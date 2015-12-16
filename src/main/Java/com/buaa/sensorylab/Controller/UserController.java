package com.buaa.sensorylab.Controller;

import com.buaa.sensorylab.Mapper.UserMapper;
import com.buaa.sensorylab.Model.NodeData;
import com.buaa.sensorylab.Model.ResponseMsg;
import com.buaa.sensorylab.Model.UserData;
import com.buaa.sensorylab.Model.UserInfo;
import com.buaa.sensorylab.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by LZJing on 2015/12/3.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<ResponseMsg<UserData>> getNewData(@RequestParam(value="userName")String userName,
                                                     @RequestParam(value="password")String password){
        logger.info("User Login Request: " + "userName=" + userName + " password=" + password);
        ResponseMsg rm = new ResponseMsg();
        try {
            UserInfo userInfo = userService.login(userName, password);

            if(password.equals(userInfo.getPassword())){
                rm.setResult(true);
                rm.setMsg("Login Success");
                rm.setData(new UserData(userInfo.getUserId(),userInfo.getUserName()));

            }else{
                rm.setResult(false);
                rm.setMsg("Login Failed");
                rm.setData(new UserData(0,""));
            }

        } catch (Exception e) {
            e.printStackTrace();
            rm.setResult(false);
            rm.setMsg("server_error");
            rm.setData(null);
        }finally {
            logger.info("return data:"+rm.getData().toString());
            return new ResponseEntity<ResponseMsg<UserData>>(rm, HttpStatus.OK);
        }
    }
}
