package com.unexpect.service.Impl;

import com.unexpect.Result.Result;
import com.unexpect.Result.ResultGenerator;
import com.unexpect.mapper.ToDoListMapper;
import com.unexpect.pojo.Employee;
import com.unexpect.pojo.ToDoList;
import com.unexpect.service.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ToDoListServiceImpl implements ToDoListService{

    @Autowired
    ToDoListMapper toDoListMapper;

//    @Override
//    public List<Log> findAllLog(Integer pageSize, Integer pageNum) {
//        Integer total=logMapper.countAllLog();
//        Map<String,Object> map=new HashMap<String,Object>();
//        map.put("pageSize",pageSize);
//        map.put("pageNum",pageNum);
//        System.out.println(logMapper.findAllLog(map));
//        return logMapper.findAllLog(map);
//    }

    @Override
    public Result getToDoListByEmployeeId(Integer employeeId) {
        Integer total=toDoListMapper.countAllToDoList(employeeId);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("employeeId",employeeId);
        List<ToDoList> todoLists=toDoListMapper.getToDoListByEmployeeId(employeeId).getToDoList();
        Map<String,Object> map2=new HashMap<String,Object>();
        map2.put("total",total);
        map2.put("todoLists",todoLists);
        Result result= ResultGenerator.genSuccessResult(map2);
        return result;
    }

    @Override
    public void delToDoList(Integer[] todoId) {
        toDoListMapper.delToDoList(todoId);
    }

    @Override
    public int addToDoList(Map<String, Object> map) {
        return toDoListMapper.addToDoList(map);
    }

    @Override
    public void updToDoList(Map<String, Object> map) {
        toDoListMapper.updToDoList(map);
    }
}
