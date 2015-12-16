package com.buaa.sensorylab.Controller;

import com.buaa.sensorylab.Mapper.NodeMapper;
import com.buaa.sensorylab.Mapper.UserMapper;
import com.buaa.sensorylab.Service.Impl.SaveNodeDataServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Controller;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by LZJing on 2015/11/7.
 */
@Controller
public class ReceiverController implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NodeMapper nodeMapper;

    @Autowired
    private SaveNodeDataServiceImpl s;

    private static Logger logger = LoggerFactory.getLogger(HelloController.class);


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(contextRefreshedEvent.getApplicationContext().getParent() == null){

            new Thread(){
                @Override
                public void run() {
                    logger.info("LZJing:Create new thread.");
                    startSocketServer();
                    super.run();
                }
            }.start();
        }
    }
    public void startSocketServer(){
        try{
            ServerSocket server = null;
            try{
                server = new ServerSocket(4700);
                logger.info("LZJing:Socket Server start up.");
            }catch(Exception e){
                logger.error("Error:can not listen to:" + e);
            }

            while(true){
                Socket client = server.accept();
                client.setSoTimeout(36000000);//20秒内没有收到数据则关闭连接。
                logger.info("LZJing:Connection build up.");
                new HandlerThread(client,s);
            }
        }catch (Exception e){
            logger.error("Error:" + e);
        }
    }

}
