package com.unexpect.service;

import com.unexpect.Result.Result;
import com.unexpect.pojo.Log;

import java.util.List;
import java.util.Map;

public interface LogService {
    public Result findAllLog(Integer pageSize, Integer pageNum);
    public Result findLogByMessage(Integer pageSize, Integer pageNum,String message);
    public void deleteAllLog();
}
