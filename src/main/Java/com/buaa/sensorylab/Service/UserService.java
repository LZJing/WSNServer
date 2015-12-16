package com.buaa.sensorylab.Service;

import com.buaa.sensorylab.Model.UserInfo;

/**
 * Created by LZJing on 2015/12/3.
 */
public interface UserService {
    UserInfo login(String userName,String password) throws Exception;
    UserInfo register(String userName,String password) throws Exception;
    UserInfo updatePw(String userName,String password) throws Exception;
}
