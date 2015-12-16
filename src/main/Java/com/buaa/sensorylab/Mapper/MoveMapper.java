package com.buaa.sensorylab.Mapper;

import com.buaa.sensorylab.Model.GPSData;
import com.buaa.sensorylab.Model.MoveType;
import com.buaa.sensorylab.Model.NodeData;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

/**
 * Created by LZJing on 2015/11/6.
 */
@Repository
public interface MoveMapper {



    //node_data±í²Ù×÷
    @Select("SELECT * FROM move_type WHERE nodeId = #{nodeId}")
    MoveType selectMoveType(int nodeId) throws Exception;

    @Update("update move_type set type = #{type} where nodeId = #{nodeId}")
    void updateMoveType(MoveType moveType) throws Exception;


}
