package com.unexpect.service.Impl;

import com.unexpect.Result.Result;
import com.unexpect.Result.ResultGenerator;
import com.unexpect.mapper.LogMapper;
import com.unexpect.pojo.Log;
import com.unexpect.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogMapper logMapper;

    @Override
    public Result findAllLog(Integer pageSize, Integer pageNum) {
//        Integer total=logMapper.countAllLog();
        Integer total=logMapper.countAllLog();
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("pageSize",pageSize);
        map.put("pageNum",pageNum);
        System.out.println(logMapper.findAllLog(map));
        List<Log> logs = logMapper.findAllLog(map);
        Map<String,Object> map2=new HashMap<>();
        map2.put("total",total);
        map2.put("logList",logs);
        Result result= ResultGenerator.genSuccessResult(map2);
        return result;
    }

    @Override
    public Result findLogByMessage(Integer pageSize, Integer pageNum,String message){
       Integer total=logMapper.countFindLogsByMessage(message);
       Map<String,Object> map=new HashMap<>();
        map.put("pageSize",pageSize);
        map.put("pageNum",pageNum);
        map.put("message",message);
        System.out.println(logMapper.findLogByMessage(map));
        List<Log>logs=logMapper.findLogByMessage(map);
        Map<String,Object> map2=new HashMap<>();
        map2.put("total",total);
        map2.put("loglist",logs);
        Result result=ResultGenerator.genSuccessResult(map2);
        return result;
    }

    @Override
    public void deleteAllLog() {
        logMapper.deleteAllLog();
    }
}
