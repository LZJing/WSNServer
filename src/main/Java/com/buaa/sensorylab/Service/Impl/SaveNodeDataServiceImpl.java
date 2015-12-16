package com.buaa.sensorylab.Service.Impl;

import com.buaa.sensorylab.Mapper.MoveMapper;
import com.buaa.sensorylab.Mapper.NodeMapper;
import com.buaa.sensorylab.Model.GPSData;
import com.buaa.sensorylab.Model.MoveType;
import com.buaa.sensorylab.Model.NodeData;
import com.buaa.sensorylab.Service.SaveNodeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.soap.Node;

/**
 * Created by LZJing on 2015/11/7.
 */
@Service
public class SaveNodeDataServiceImpl implements SaveNodeDataService{

    @Autowired
    private NodeMapper nodeMapper;
    @Autowired
    private MoveMapper moveMapper;


    @Override
    public void insertReceivedData(NodeData nodeData,GPSData gpsData) throws Exception {
        try {
            //判断GPS有无数据，如果没有，就直接关联到上一个有效的数据上。
            if(gpsData.getGpsLo()==0&&gpsData.getGpsLa()==0){
                NodeData n = nodeMapper.selectLastNodeDataByNodeId(nodeData.getNodeId());
                nodeData.setGpsId(gpsData.getGpsId());
                nodeMapper.insertNodeData(nodeData);
            }else{
                nodeMapper.insertGPSData(gpsData);
                nodeData.setGpsId(gpsData.getGpsId());
                nodeMapper.insertNodeData(nodeData);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public MoveType findMoveTypeById(int id) throws Exception {

        return moveMapper.selectMoveType(id);
    }

}
