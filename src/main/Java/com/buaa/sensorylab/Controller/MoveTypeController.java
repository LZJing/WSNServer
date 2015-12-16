package com.buaa.sensorylab.Controller;

import com.buaa.sensorylab.Mapper.MoveMapper;
import com.buaa.sensorylab.Mapper.NodeMapper;
import com.buaa.sensorylab.Mapper.UserMapper;
import com.buaa.sensorylab.Model.GPSData;
import com.buaa.sensorylab.Model.MoveType;
import com.buaa.sensorylab.Model.NodeData;
import com.buaa.sensorylab.Model.ResponseMsg;
import com.buaa.sensorylab.Service.Impl.SaveNodeDataServiceImpl;
import com.buaa.sensorylab.Service.RequestNodeDataService;
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
 * Created by LZJing on 2015/11/10.
 */
@Controller
@RequestMapping("/move")
public class MoveTypeController {

    @Autowired
    private MoveMapper moveMapper;

    private static Logger logger = LoggerFactory.getLogger(MoveTypeController.class);

    @RequestMapping(value = "/type",method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<ResponseMsg<MoveType>> getMoveType(@RequestParam(value="nodeId")int nodeId){
        logger.info("New Data Request: " + "nodeId=" + nodeId);
        ResponseMsg rm = new ResponseMsg();
        try {
            MoveType moveType = moveMapper.selectMoveType(nodeId);
            if(moveType == null){
                rm.setResult(false);
                rm.setMsg("error");
                rm.setData(moveType);
            }else{
                rm.setResult(true);
                rm.setMsg("success");
                rm.setData(moveType);
            }
        } catch (Exception e) {
            e.printStackTrace();
            rm.setResult(false);
            rm.setMsg("server_error");
            rm.setData(null);
        }finally {
            return new ResponseEntity<ResponseMsg<MoveType>>(rm, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/changetype",method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<ResponseMsg<MoveType>> setMoveType(@RequestParam(value="nodeId")int nodeId,@RequestParam(value="type")int type){
        logger.info("New Data Request: " + "nodeId=" + nodeId);
        ResponseMsg rm = new ResponseMsg();
        MoveType moveType = new MoveType(nodeId,type);
        try {
            moveMapper.updateMoveType(moveType);
            rm.setResult(true);
            rm.setMsg("success");
            rm.setData(moveType);

        } catch (Exception e) {
            e.printStackTrace();
            rm.setResult(false);
            rm.setMsg("server_error");
            rm.setData(null);
        }finally {
            return new ResponseEntity<ResponseMsg<MoveType>>(rm, HttpStatus.OK);
        }
    }
}
