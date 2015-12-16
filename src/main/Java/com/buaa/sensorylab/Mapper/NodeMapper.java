package com.buaa.sensorylab.Mapper;

import com.buaa.sensorylab.Model.GPSData;
import com.buaa.sensorylab.Model.NodeData;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by LZJing on 2015/11/6.
 */
@Repository
public interface NodeMapper {



    //node_data表操作
    @Select("SELECT * FROM node_data WHERE dataId = #{dataId}")
    NodeData findNodeDataById(int dataId) throws Exception;

    @Select("SELECT * FROM node_data WHERE dataId in #{dataIds}")
    NodeData findNodeDatasByIds(int[] dataIds) throws Exception;

    @Insert("insert into node_data(nodeId,userId,parentId,hop,lastTime,reserve,light,temperature,humidity,longitude,isEast,latitude,isNorth,gpsId) " +
            "values(#{nodeId},#{userId},#{parentId},#{hop},#{lastTime},#{reserve},#{light},#{temperature},#{humidity},#{longitude},#{isEast},#{latitude},#{isNorth},#{gpsId})")
    @SelectKey(before = false,keyProperty = "dataId",resultType = Integer.class,statementType = StatementType.STATEMENT,statement = "select LAST_INSERT_ID()")
    void insertNodeData(NodeData nodeData) throws Exception;

    //gps_data表操作
    @Select("SELECT * FROM gps_data WHERE gpsId = #{gpsId}")
    GPSData findGpsDataById(int gpsId) throws Exception;


    @Insert("insert into gps_data(dateTime,gpsLo,gpsIsEast,gpsLa,gpsIsNorth,altitude,speed) " +
            "values(#{dateTime},#{gpsLo},#{gpsIsEast},#{gpsLa},#{gpsIsNorth},#{altitude},#{speed})")
    @SelectKey(before = false,keyProperty = "gpsId",resultType = Integer.class,statementType = StatementType.STATEMENT,statement = "select LAST_INSERT_ID()")
    void insertGPSData(GPSData gpsData) throws Exception;

    //选出某节点的最后一个数据
    @Select("SELECT * FROM node_data WHERE nodeId = #{nodeId} and gpsId >1 order by lastTime desc limit 1")
    NodeData selectLastNodeDataByNodeId(int nodeId) throws Exception;

}
