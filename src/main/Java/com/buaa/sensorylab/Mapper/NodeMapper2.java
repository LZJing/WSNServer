package com.buaa.sensorylab.Mapper;

import com.buaa.sensorylab.Model.GPSData;
import com.buaa.sensorylab.Model.NodeData;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by LZJing on 2015/11/6.
 */
@Repository
public interface NodeMapper2 {

    NodeData findNodeDataById(int dataId) throws Exception;

    List<NodeData>  findMultiNodeDataByIds(int[] nodeIds) throws Exception;

    /**
     * 查询每个节点的最新数据。
     * @param params nodeIds--查询的节点编号数组，startTime--查询起始时间
     * @return 返回查询结果集的列表
     * @throws Exception
     */
    List<NodeData>  findMultiNodeDataByIdsMap(Map<String,Object> params) throws Exception;

    /**
     * 查询指定单个节点在某一时间段内的历史数据
     * @param params nodeId--节点编号，startTime--查询起始时间，endTime--查询结束时间
     * @return 返回查询结果集列表
     * @throws Exception
     */
    List<NodeData>  findMultiNodeDataByIdAndTimeMap(Map<String,Object> params) throws Exception;


}
