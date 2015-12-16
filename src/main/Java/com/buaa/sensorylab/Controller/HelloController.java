package com.buaa.sensorylab.Controller;


import com.buaa.sensorylab.Mapper.NodeMapper;
import com.buaa.sensorylab.Mapper.NodeMapper2;
import com.buaa.sensorylab.Mapper.UserMapper;
import com.buaa.sensorylab.Model.GPSData;
import com.buaa.sensorylab.Model.NodeData;
import com.buaa.sensorylab.Model.UserInfo;
import com.buaa.sensorylab.Service.Impl.SaveNodeDataServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by LZJing on 2015/10/24.
 */
@Controller
@RequestMapping("/hello")
public class HelloController {

//    @Autowired
//    private UserMapper userMapper;
//    @Autowired
//    private NodeMapper nodeMapper;
    @Autowired
    private NodeMapper2 nodeMapper2;

    @Autowired
    private SaveNodeDataServiceImpl s;



    private static Logger logger = LoggerFactory.getLogger(HelloController.class);
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    @ResponseBody
    public Object hello(){
        logger.debug("******************");
        System.out.println("%%%%%%%%%%%%%%");
//        UserInfo userInfo =null;
//        NodeData nodeData = null;
//        GPSData gpsData = null;
//        List<NodeData> list = null;
//        try {
////            userInfo= userMapper.findUserById(1);
//            int[] a = {1,2,35,123};
//            long lastTime = 100000;
//
//            Map<String, Object> params = new HashMap<String, Object>();
//            params.put("lastTime",lastTime);
//            params.put("nodeIds",a);
//            list = new ArrayList<NodeData>();
//            //list = nodeMapper2.findMultiNodeDataByIds(a);
////            gpsData = nodeMapper.findGpsDataById(1200);
////            s.insertReceivedData(nodeData,gpsData);
//            list = nodeMapper2.findMultiNodeDataByIdsMap(params);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return "Hello";
    }





}
