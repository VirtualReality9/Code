package com.unexpect.controller;

import com.unexpect.Result.Result;
import com.unexpect.pojo.Employee;
import com.unexpect.pojo.User;
import com.unexpect.service.EmployeeService;
import com.unexpect.service.UserService;
import com.unexpect.utils.SaltUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Resource
    UserService userService;
    @Resource
    EmployeeService employeeService;

    private static Logger logger= LogManager.getLogger(ToDoListController.class);

    @PostMapping("/user/update")
    @ResponseBody
    public Result updateUser(@RequestBody  Map map){
        logger.info("进行用户修改操作");
        // try {
            if(map.get("userPassword") != null){
                String md5HashPassword = new Md5Hash(map.get("oldPassword").toString(),map.get("salt").toString(), 1024).toString();
                if(md5HashPassword.equals(map.get("userPassword"))){
                    //1.生产随机盐
                    String newSalt = SaltUtils.getSalt(8);
                    //2.将随机盐保存到数据
                    map.put("salt", newSalt);
                    //3.明文密码进行md5 + salt + hash散列
                    String newMd5HashPassword = new Md5Hash(map.get("newPassword").toString(),map.get("salt").toString(), 1024).toString();
                    System.out.println("password:" + map.get("newPassword").toString());
                    System.out.println("newMd5HashPassword" + newMd5HashPassword);
                    map.put("userPassword", newMd5HashPassword);
                }else{
                    return Result.succ("你的密码有误");
                }
            }
            userService.updateUser(map);
            return Result.succ("修改成功");
        // }catch (Exception e){
        //     logger.info("修改错误");
        //     return Result.fail("修改错误");
        // }
        // employeeService.updateEmployee(map);

    }

    @GetMapping("/user/queryUserById")
    @ResponseBody
    public Result queryUserById(@RequestParam("userId") Integer userId){
        logger.info("进行用户修改查询");
        try {
            // User user = userService.queryUserById(userId);
            // HashMap hashMap = new HashMap();
            // Employee employee = employeeService.queryEmployeeByUserId(userId);
            User user =  userService.queryUserById(userId);
            // hashMap.put("employee",employee);
            // hashMap.put("user",user);
            // employeeService.updateEmployee(map);
            return Result.succ("查询成功",user);
        }catch (Exception e){
            logger.error("查询异常");
            return Result.fail("查询失败");
        }
    }

}
