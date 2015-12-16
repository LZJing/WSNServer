package com.buaa.sensorylab.Controller;

import com.buaa.sensorylab.Mapper.NodeMapper;
import com.buaa.sensorylab.Mapper.UserMapper;
import com.buaa.sensorylab.Model.GPSData;
import com.buaa.sensorylab.Model.NodeData;
import com.buaa.sensorylab.Model.ResponseMsg;
import com.buaa.sensorylab.Model.UserInfo;
import com.buaa.sensorylab.Service.Impl.SaveNodeDataServiceImpl;
import com.buaa.sensorylab.Service.RequestNodeDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

/**
 * Created by LZJing on 2015/11/10.
 */
@Controller
@RequestMapping("/nodedata")
public class NodeDataController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NodeMapper nodeMapper;
    @Autowired
    private SaveNodeDataServiceImpl s;

    @Autowired
    private RequestNodeDataService re;

    private static Logger logger = LoggerFactory.getLogger(NodeDataController.class);

    @RequestMapping(value = "/newdata",method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<ResponseMsg<NodeData>> getNewData(@RequestParam(value="nodeIds")String nodeIds,
                                                     @RequestParam(value="startTime")Long startTime){
        logger.info("New Data Request: " + "nodeIds=" + nodeIds + " startTime=" + startTime);
        ResponseMsg rm = new ResponseMsg();
        try {
            List<NodeData> nd = re.getNewNodeData(startTime, getNodesId(nodeIds));
            if(nd == null){
                rm.setResult(false);
                rm.setMsg("error");
                rm.setData(nd);
            }else{
                rm.setResult(true);
                rm.setMsg("success");
                rm.setData(nd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            rm.setResult(false);
            rm.setMsg("server_error");
            rm.setData(null);
        }finally {
            return new ResponseEntity<ResponseMsg<NodeData>>(rm, HttpStatus.OK);
        }
    }
    @RequestMapping(value = "/hisdata",method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<ResponseMsg<NodeData>> getHistoryData(@RequestParam(value = "nodeId")Integer nodeId,
                                                        @RequestParam(value = "startTime")Long startTime,
                                                        @RequestParam(value = "endTime")Long endTime){

        logger.info("History Data Request: " + "nodeId=" + nodeId + " startTime=" + startTime+" endTime="+endTime);
        ResponseMsg rm = new ResponseMsg();
        try {
            //List<NodeData> nd = re.getNewNodeData(startTime, getNodesId(nodeIds));
            List<NodeData> nd = re.getHistoryNodeData(startTime, endTime, nodeId);
            if(nd == null){
                rm.setResult(false);
                rm.setMsg("error");
                rm.setData(nd);
            }else{
                rm.setResult(true);
                rm.setMsg("success");
                rm.setData(nd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            rm.setResult(false);
            rm.setMsg("server_error");
            rm.setData(null);
        }finally {
            return new ResponseEntity<ResponseMsg<NodeData>>(rm, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/gpsdata",method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<ResponseMsg<GPSData>> getGPSData(@RequestParam(value = "gpsId")Integer gpsId){

        logger.info("GPS Data Request: " + "gpsId=" + gpsId );
        ResponseMsg rm = new ResponseMsg();
        try {
            GPSData nd = re.getGpsData(gpsId);
            if(nd == null){
                rm.setResult(false);
                rm.setMsg("error");
                rm.setData(nd);
            }else{
                rm.setResult(true);
                rm.setMsg("success");
                rm.setData(nd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            rm.setResult(false);
            rm.setMsg("server_error");
            rm.setData(null);
        }finally {
            return new ResponseEntity<ResponseMsg<GPSData>>(rm, HttpStatus.OK);
        }
    }

    private int[] getNodesId(String nodeIds) {
        String[] strs = nodeIds.split("#");
        int[] result = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            result[i] = Integer.parseInt(strs[i]);
        }
        return result;
    }
}
