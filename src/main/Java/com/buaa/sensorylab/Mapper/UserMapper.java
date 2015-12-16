package com.buaa.sensorylab.Mapper;

import com.buaa.sensorylab.Model.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

/**
 * Created by LZJing on 2015/11/5.
 */
@Repository
public interface UserMapper {

    @Select("SELECT * FROM user_info WHERE userId = #{userId}")
    public UserInfo findUserById(int userId) throws Exception;

    @Select("SELECT * FROM user_info WHERE userName = #{userName}")
    public UserInfo findUserByUserName(String userName) throws Exception;

    @Insert("insert into user_info(userName,password) values(#{userName},#{password})")
    @SelectKey(before = false,keyProperty = "userId",resultType = Integer.class,statementType = StatementType.STATEMENT,statement = "select LAST_INSERT_ID()")
    public void insertUser(UserInfo userInfo) throws Exception;

}
