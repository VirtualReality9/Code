package com.unexpect.mapper;

import com.unexpect.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Mapper
public interface ToDoListMapper {

    public Employee getToDoListByEmployeeId(Integer employeeId);

    public void delToDoList(Integer[] todoId);

    public int addToDoList(Map<String,Object> map);

    public void updToDoList(Map<String, Object> map);

    Integer countAllToDoList(Integer employeeId);

}
