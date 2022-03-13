package com.unexpect.controller;


import com.unexpect.Result.Result;
import com.unexpect.pojo.Department;
import com.unexpect.pojo.Employee;
import com.unexpect.pojo.Meta;
import com.unexpect.pojo.User;
import com.unexpect.service.DepartmentService;
import com.unexpect.service.EmployeeService;
import com.unexpect.service.UserService;
import com.unexpect.utils.SaltUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin
public class LoginController {
    Meta meta = new Meta();
    @Resource
    UserService userService;
    @Resource
    EmployeeService employeeService;

    private static Logger logger= LogManager.getLogger(LoginController.class);

    @RequestMapping("/toLogin")
    @ResponseBody
    public Result tologin(){
        return Result.fail(404,"没有登录");
    }
    /**
     *
     * @param map 请求登录参数Map
     * @return 返回数据 data,token,meta
     */

    @PostMapping ("/login")
    @ResponseBody
    public HashMap login(@RequestBody Map map){
        logger.info("进行登录操作");
        HashMap<String, Object> result = new HashMap<String, Object>();
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录信息
        //令牌加密
        UsernamePasswordToken token = new UsernamePasswordToken(map.get("phone").toString(),map.get("password").toString());
        User user = userService.queryUserByMobile(map.get("phone").toString());
        if(user != null){
            result.put("data",user);
            User EmployeeUser = userService.queryNavigationBars(token.getUsername());
            if(EmployeeUser != null) {
                Employee employee = employeeService.queryUserDepartment(EmployeeUser.getEmployee().getEmployeeId());
                result.put("data",EmployeeUser);
                result.put("employee",employee);
            }
        }
        try {
            subject.login(token);//执行登录方法,如果没有异常说明OK了
            meta.setStatus(200);
            meta.setMsg("登录成功");
            result.put("meta", meta);
            return result;
        } catch (UnknownAccountException e) {//用户名不存在
            // model.addAttribute("msg","用户名错误");
            meta.setStatus(200);
            meta.setMsg("用户名不存在");
            result.put("meta",meta);
            return result;
        }catch (IncorrectCredentialsException e){//密码错误
            // model.addAttribute("msg","密码错误");
            meta.setStatus(200);
            meta.setMsg("密码错误");
            result.put("meta",meta);
            return result;
        }
    }

    /**
     * 登出
     * @return
     */
    @GetMapping("/logout")
    @ResponseBody
    public Result logout(){
        logger.info("进行登出操作");
        Subject subject = SecurityUtils.getSubject();
        // if (subject.isAuthenticated()) {
            subject.logout();
            System.out.println("logout!");
        // }
        return Result.succ("退出成功");
    }

    /**
     * 注册
     * @param map
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    public HashMap register(@RequestBody Map map){
        logger.info("进行注册操作");
        HashMap hashMap = new HashMap();
        try {
            User user = userService.queryUserByMobile(map.get("phone").toString());
            System.out.println(map.get("phone").toString());
            if(user != null) {
                hashMap.put("meta", new Meta(200, "手机号已注册"));
                return hashMap;
            }
            //1.生产随机盐
            String salt = SaltUtils.getSalt(8);
            //2.将随机盐保存到数据
            map.put("salt", salt);
            //3.明文密码进行md5 + salt + hash散列
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String userCreateTime = simpleDateFormat.format(new Date());
            String md5HashPassword = new Md5Hash(map.get("password").toString(), salt, 1024).toString();
            map.put("userPhone",map.get("phone").toString());
            map.put("userName",map.get("userName").toString());
            map.put("userCreateTime",userCreateTime);
            map.put("userPassword", md5HashPassword);
            Integer userId = userService.addUser(map);
            map.put("userId",userId);
            map.put("roleId",21);
            userService.userAddRole(map);
        }catch (Exception e){
            logger.error("注册账号时异常！！！");
            meta.setMsg("注册失败");
            meta.setStatus(200);
            hashMap.put("meta",meta);
        }
        meta.setMsg("找回密码成功");
        meta.setStatus(200);
        hashMap.put("meta",meta);
        return hashMap;
    }

    /**
     * 找回密码
     * @param map
     * @return
     */
    @PostMapping("/retrievePassword")
    @ResponseBody
    public HashMap retrievePassword(@RequestBody Map map){
        logger.info("进行找回密码操作");
        HashMap hashMap = new HashMap();
        try {
            User user = userService.queryUserByMobile(map.get("phone").toString());
            if(user == null) {
                hashMap.put("meta", new Meta(200, "手机号不存在"));
                return hashMap;
            }
            //1.生产随机盐
            String salt = SaltUtils.getSalt(8);
            //2.将随机盐保存到数据
            map.put("salt", salt);
            //3.明文密码进行md5 + salt + hash散列
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String userCreateTime = simpleDateFormat.format(new Date());
            String md5HashPassword = new Md5Hash(map.get("password").toString(), salt, 1024).toString();
            System.out.println("password:" + map.get("password").toString());
            map.put("userId",user.getUserId());
            map.put("userPassword", md5HashPassword);
            userService.updateUser(map);
        }catch (Exception e){
            logger.error("找回密码时修改密码异常！！！");
            meta.setMsg("修改失败");
            meta.setStatus(200);
            hashMap.put("meta",meta);
        }
        meta.setMsg("注册成功");
        meta.setStatus(200);
        hashMap.put("meta",meta);
        return hashMap;
    }

    /**
     * 未授权
     * @return
     */
    @GetMapping("/noauth")
    @ResponseBody
    public HashMap unauthorized(){
        logger.info("进行了未授权的访问");
        HashMap hashMap = new HashMap();
        meta.setStatus(302);
        meta.setMsg("没有权限！！！");
        hashMap.put("meta",meta);
        return hashMap;
    }

    /**
     * 菜单
     * @param id
     * @return
     */
    @GetMapping("/menus")
    @ResponseBody
    public Map<String,Object> breturn(@RequestParam(value = "id" ,required = false)Integer id){
        HashMap result = new HashMap();
        try {
            User user = userService.queryManagerNavigationBars(id);
            meta.setStatus(200);
            meta.setMsg("查询成功");
            result.put("data",user);
            result.put("meta",meta);
            // return model;
        } catch (UnknownAccountException e) {//用户名不存在
            meta.setMsg("用户名错误");
            result.put("meta",meta);
            return result;

        }catch (IncorrectCredentialsException e){//密码错误
            meta.setMsg("密码错误");
            result.put("meta",meta);
            return result;
        }
        return  result;
    }

}
