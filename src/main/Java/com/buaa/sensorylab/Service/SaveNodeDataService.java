package com.buaa.sensorylab.Service;

import com.buaa.sensorylab.Model.GPSData;
import com.buaa.sensorylab.Model.MoveType;
import com.buaa.sensorylab.Model.NodeData;

/**
 * Created by LZJing on 2015/11/6.
 */
public interface SaveNodeDataService {

    public void insertReceivedData(NodeData nodeData,GPSData gpsData) throws Exception;

    public MoveType findMoveTypeById(int id) throws Exception;
}
