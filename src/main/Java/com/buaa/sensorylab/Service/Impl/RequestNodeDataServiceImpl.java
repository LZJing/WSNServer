package com.buaa.sensorylab.Service.Impl;

import com.buaa.sensorylab.Mapper.NodeMapper;
import com.buaa.sensorylab.Mapper.NodeMapper2;
import com.buaa.sensorylab.Model.GPSData;
import com.buaa.sensorylab.Model.NodeData;
import com.buaa.sensorylab.Service.RequestNodeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LZJing on 2015/11/10.
 */
@Service
public class RequestNodeDataServiceImpl implements RequestNodeDataService {
    @Autowired
    private NodeMapper nodeMapper;
    @Autowired
    private NodeMapper2 nodeMapper2;

    @Override
    public List<NodeData> getNewNodeData(Long startTime, int[] nodesId) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("startTime",startTime);
        params.put("nodeIds",nodesId);
        return nodeMapper2.findMultiNodeDataByIdsMap(params);
    }

    @Override
    public List<NodeData> getHistoryNodeData(Long startTime, Long endTime, int nodesId) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("endTime",endTime);
        params.put("startTime",startTime);
        params.put("nodeId",nodesId);
        return nodeMapper2.findMultiNodeDataByIdAndTimeMap(params);
    }


    @Override
    public GPSData getGpsData(int gpsId) throws Exception {



        return nodeMapper.findGpsDataById(gpsId);
    }
}
