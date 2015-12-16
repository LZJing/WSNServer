package com.buaa.sensorylab.Controller;

import com.buaa.sensorylab.Model.GPSData;
import com.buaa.sensorylab.Model.MoveType;
import com.buaa.sensorylab.Model.NodeData;
import com.buaa.sensorylab.Service.Impl.SaveNodeDataServiceImpl;
import com.buaa.sensorylab.Service.SaveNodeDataService;
import com.buaa.sensorylab.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Calendar;

/**
 * Created by LZJing on 2015/11/7.
 */
public class HandlerThread implements Runnable{
    public static byte[] move = {
            (byte) 0x7E ,(byte) 0x44 ,(byte) 0x00 , (byte) 0x00 ,
            (byte) 0xFF ,(byte) 0xFF , (byte) 0x00 ,(byte) 0x00 ,
            (byte) 0x03 , (byte) 0x3F ,(byte) 0xAA ,(byte) 0x00 ,
            (byte) 0x00 ,(byte) 0xEE ,(byte) 0x8E , (byte) 0x87 ,
            (byte) 0x7E};
    public static byte[] stop = {
            (byte) 0x7E ,(byte) 0x44 ,(byte) 0x00 , (byte) 0x00,
            (byte) 0xFF,(byte) 0xFF, (byte) 0x00 ,(byte) 0x00 ,
            (byte) 0x03, (byte) 0x3F ,(byte) 0xAA ,(byte) 0x00,
            (byte) 0x00 ,(byte) 0xFF ,(byte) 0x9E, (byte) 0x85 ,
            (byte) 0x7E};
    private Socket socket;
    SaveNodeDataServiceImpl s;
    private static Logger logger = LoggerFactory.getLogger(HelloController.class);

    public HandlerThread(Socket socket,SaveNodeDataServiceImpl s) {
        this.socket = socket;
        this.s = s;
        new Thread(this).start();
    }

    public void run() {

        DataInputStream dis = null;
        DataOutputStream dos = null;
        try{

            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());

            while(isConnected(socket)) {


                byte[] twoBytes = new byte[2];
                dis.read(twoBytes);
                int nodeId;
                if(twoBytes[0]==126&&twoBytes[1]==69){
                    NodeData nodeData = new NodeData();
                    byte[] inputBytes55= new byte[55];               //Read 57 bytes --> inputButes
                    dis.read(inputBytes55);
                    if(inputBytes55[54]==126){
                        resolve(inputBytes55, 2);
                        nodeId = Tools.getShort(inputBytes55, 10 - 2);
                    }else{
                        logger.info("LZJing:Receive wrong data package, drop!");
                        continue;
                    }

                }else if (twoBytes[0]==126&&twoBytes[1]==126){
                    NodeData nodeData = new NodeData();
                    byte[] inputByte56 =new byte[56];
                    dis.read(inputByte56);
                    if(inputByte56[55]==126){
                        resolve(inputByte56,1);
                        nodeId = Tools.getShort(inputByte56, 10 - 1);
                    }else {
                        logger.info("LZJing:Receive wrong data package, drop!");
                        continue;
                    }

                }else{
                    continue;
                }

                //Response
                MoveType moveType = s.findMoveTypeById(nodeId);


                byte[] response = moveType.getType()==0?stop:move;
                dos.write(response);
                dos.flush();

            }

            dis.close();
            dos.close();
        }catch (Exception e){
            logger.error("Error:Server run error:"+e.getMessage());
        }finally {
            if(socket!=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void resolve(byte[] inputBytes, int i) {
        NodeData nodeData = new NodeData();
        nodeData.setNodeId(Tools.getShort(inputBytes, 10 - i));
        nodeData.setUserId(1);//特别注意设置用户ID
        nodeData.setParentId(Tools.getShort(inputBytes, 12 - i));
        nodeData.setLastTime(System.currentTimeMillis());
        nodeData.setHop(inputBytes[14 - i]);
        nodeData.setReserve(Tools.getShort(inputBytes, 15 - i));
        nodeData.setLight(Tools.getShort(inputBytes, 17 - i));
        nodeData.setTemperature(Tools.getShort(inputBytes, 19 - i));
        nodeData.setHumidity(Tools.getShort(inputBytes, 21 - i));
        nodeData.setLongitude(Tools.byteArrayToInt(inputBytes, 23 - i));
        nodeData.setIsEast((inputBytes[27-i]==0x00)?false:true);
        nodeData.setLatitude(Tools.byteArrayToInt(inputBytes, 28 - i));
        nodeData.setIsNorth((inputBytes[32-i]==0x00)?false:true);

        GPSData gpsData = new GPSData();
        short year = Tools.getShort(inputBytes, 33 - i);
        byte month = inputBytes[35- i];
        byte day = inputBytes[36- i];
        byte hour = inputBytes[37- i];
        byte minute = inputBytes[38- i];
        byte second = inputBytes[39- i];

        if(year>=2014&&month<=12&&day<=31&&hour<=24){//GPS时间数据有效
            Calendar c = Calendar.getInstance();
            c.set(year, month, day, hour, minute, second);
            gpsData.setDateTime(c.getTimeInMillis());
        }else{//GPS时间数据无效
            gpsData.setDateTime(0);
        }


        double gLo = ((double)Tools.byteArrayToInt(inputBytes, 40 - i))/10000000;
        boolean gIsE = (inputBytes[44-i]==0x00)?false:true;
        double gLa = ((double)Tools.byteArrayToInt(inputBytes, 45 - i))/10000000;
        boolean gIsN = (inputBytes[49-i]==0x00)?false:true;
        double al = ((double)Tools.getShort(inputBytes, 50 - i))/10;
        double spe = (double)Tools.getShort(inputBytes, 52 - i);

        gpsData.setGpsLo(gLo);
        gpsData.setGpsIsEast(gIsE);
        gpsData.setGpsLa(gLa);
        gpsData.setGpsIsNorth(gIsN);
        gpsData.setAltitude(al);
        gpsData.setSpeed(spe);

        logger.info("LZJing:Receive new node data:" + nodeData.toString());
        logger.info("LZJing:Receive new gps data:" + gpsData.toString());
        try {
            s.insertReceivedData(nodeData, gpsData);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean isConnected(Socket s){
        try{
            s.sendUrgentData(0xFF);
            return true;
        }catch(Exception e){
            return false;
        }

    }



}