package com.unexpect.controller;


import com.unexpect.Result.Result;
import com.unexpect.Result.ResultGenerator;
import com.unexpect.pojo.Log;
import com.unexpect.service.Impl.LogServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
public class LogController {
    @Resource
    LogServiceImpl logService;

    private static Logger logger= LogManager.getLogger(LogController.class);

    @RequestMapping("/log/findAllLog")
    @ResponseBody
    //Integer pageSize, Integer pageNum
    public Result findAllLog(Integer pageSize, Integer pageNum) {
        logger.info("进行日志查询操作");
        Result result=logService.findAllLog(pageSize,pageNum);
        System.out.println("传回去的="+result);
        return result;
    }

    @RequestMapping("/log/findLogsByMessage")
    public Result findLogsByMessage(Integer pageSize,Integer pageNum,String message){
        logger.info("根据消息查询日志");
        Result result=logService.findLogByMessage(pageSize,pageNum,message);
        System.out.println(result);
        return result;
    }

    @RequestMapping("/log/deleteAllLog")
    public void deleteAllLog() {
        logger.info("日志删除操作");
        logService.deleteAllLog();
    }
}
