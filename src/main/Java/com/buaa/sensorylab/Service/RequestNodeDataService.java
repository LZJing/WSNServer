package com.buaa.sensorylab.Service;

import com.buaa.sensorylab.Model.GPSData;
import com.buaa.sensorylab.Model.NodeData;

import java.util.List;

/**
 * Created by LZJing on 2015/11/10.
 */
public interface RequestNodeDataService {

    /**
     * 获取最新数据
     * @param startTime
     * @param nodesId
     * @return
     * @throws Exception
     */
    List<NodeData> getNewNodeData(Long startTime,int[] nodesId) throws Exception;

    /**
     * 获取历史数据
     * @param startTime
     * @param endTime
     * @param nodesId
     * @return
     * @throws Exception
     */
    List<NodeData> getHistoryNodeData(Long startTime,Long endTime,int nodesId) throws Exception;

    /**
     * 获得GPS数据
     * @param gpsId 数据编号
     * @return
     * @throws Exception
     */
    GPSData getGpsData(int gpsId) throws Exception;

}
