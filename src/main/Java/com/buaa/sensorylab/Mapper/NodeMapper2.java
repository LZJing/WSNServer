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
     * ��ѯÿ���ڵ���������ݡ�
     * @param params nodeIds--��ѯ�Ľڵ������飬startTime--��ѯ��ʼʱ��
     * @return ���ز�ѯ��������б�
     * @throws Exception
     */
    List<NodeData>  findMultiNodeDataByIdsMap(Map<String,Object> params) throws Exception;

    /**
     * ��ѯָ�������ڵ���ĳһʱ����ڵ���ʷ����
     * @param params nodeId--�ڵ��ţ�startTime--��ѯ��ʼʱ�䣬endTime--��ѯ����ʱ��
     * @return ���ز�ѯ������б�
     * @throws Exception
     */
    List<NodeData>  findMultiNodeDataByIdAndTimeMap(Map<String,Object> params) throws Exception;


}
