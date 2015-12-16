package com.buaa.sensorylab.Service.Impl;

import com.buaa.sensorylab.Mapper.NodeMapper;
import com.buaa.sensorylab.Mapper.UserMapper;
import com.buaa.sensorylab.Model.UserInfo;
import com.buaa.sensorylab.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LZJing on 2015/12/3.
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserInfo login(String userName, String password) throws Exception {
        UserInfo userInfo = userMapper.findUserByUserName(userName);

        return userInfo;
    }

    @Override
    public UserInfo register(String userName, String password) throws Exception {
        return null;
    }

    @Override
    public UserInfo updatePw(String userName, String password) throws Exception {
        return null;
    }
}
