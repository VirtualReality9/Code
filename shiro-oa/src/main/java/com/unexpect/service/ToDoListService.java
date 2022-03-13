package com.unexpect.service;

import com.unexpect.Result.Result;
import com.unexpect.pojo.Employee;

import java.util.Map;

public interface ToDoListService {

    public Result getToDoListByEmployeeId(Integer employeeId);

    public void delToDoList(Integer[] todoId);

    public int addToDoList(Map<String,Object> map);

    public void updToDoList(Map<String, Object> map);

}
