package com.buaa.sensorylab.Service;

import com.buaa.sensorylab.Model.GPSData;
import com.buaa.sensorylab.Model.NodeData;

import java.util.List;

/**
 * Created by LZJing on 2015/11/10.
 */
public interface RequestNodeDataService {

    /**
     * ��ȡ��������
     * @param startTime
     * @param nodesId
     * @return
     * @throws Exception
     */
    List<NodeData> getNewNodeData(Long startTime,int[] nodesId) throws Exception;

    /**
     * ��ȡ��ʷ����
     * @param startTime
     * @param endTime
     * @param nodesId
     * @return
     * @throws Exception
     */
    List<NodeData> getHistoryNodeData(Long startTime,Long endTime,int nodesId) throws Exception;

    /**
     * ���GPS����
     * @param gpsId ���ݱ��
     * @return
     * @throws Exception
     */
    GPSData getGpsData(int gpsId) throws Exception;

}
