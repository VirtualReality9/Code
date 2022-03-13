package com.unexpect.controller;

import com.unexpect.Result.Result;
import com.unexpect.Result.ResultGenerator;
import com.unexpect.Result.Result;
import com.unexpect.Result.ResultGenerator;
import com.unexpect.service.ToDoListService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class ToDoListController {

    @Autowired
    ToDoListService toDoListService;

    private static Logger logger= LogManager.getLogger(ToDoListController.class);

    @GetMapping("/todolist/query")
    public Result getToDoListByEmployeeId(@RequestParam("employeeId") Integer employeeId){
        //return toDoListService.getToDoListByEmployeeId(2);
        logger.info("查询待办事项。");
//        Employee employee=toDoListService.getToDoListByEmployeeId(employeeId,pageSize,pageNum);
        Result result= ResultGenerator.genSuccessResult(toDoListService.getToDoListByEmployeeId(employeeId));
        System.out.println("employeeId="+employeeId);
        System.out.println(result);
        return result;
    }

    @RequestMapping("/todolist/delete")
    //@RequestParam("todoId")Integer[] todoId
    public String delete(@RequestParam("todoId")Integer[] todoId){
//        Integer[] todoId = new Integer[10];
//        todoId[0] = 1;
        System.out.println(todoId);
        toDoListService.delToDoList(todoId);
        logger.info("删除待办事项。");
        return "redirect:/employee/list";
    }
//

//
    @RequestMapping("/todolist/add")
    @ResponseBody
    public Result add(@RequestBody Map map){
        logger.info("添加待办事项。");
        System.out.println("map="+map);
        System.out.println("得到的="+map.get("todoTime"));
        System.out.println("得到的="+map.get("todoContent"));
        Result result= ResultGenerator.genSuccessResult(toDoListService.addToDoList(map));
        logger.info("添加待办事项。");
        return result;
    }
//

    @RequestMapping("/todolist/update")
    //@RequestParam("map") Map map
    public  String update(@RequestBody Map<String,Object> map){
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("todoId",8);
//        map.put("todoNote","紧急");
        Result result= ResultGenerator.genSuccessResult(toDoListService.addToDoList(map));
        toDoListService.updToDoList(map);
        logger.info("更改待办事项。");
        return "redirect:/employee/tomain";
    }

}
