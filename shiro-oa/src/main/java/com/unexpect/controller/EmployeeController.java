package com.unexpect.controller;


import com.unexpect.pojo.Employee;
import com.unexpect.pojo.Meta;
import com.unexpect.pojo.Role;
import com.unexpect.pojo.User;
import com.unexpect.service.EmployeeService;
import com.unexpect.service.RoleService;
import com.unexpect.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {

    Meta meta = new Meta();

    @Resource
    EmployeeService employeeService;
    @Resource
    RoleService roleService;
    @Resource
    UserService userService;

    private static Logger logger= LogManager.getLogger(EmployeeController.class);

    /**
     *
     * @param query 查询参数
     * @param pagenum 页号
     * @param pagesize 页大小
     * @return 返回数据 meta,total,data
     */
    @GetMapping("/staffInfo")
    @ResponseBody
    public HashMap tolist(@RequestParam(value = "query",required = false)String query,
                          @RequestParam("pagenum")Integer pagenum,
                          @RequestParam("pagesize")Integer pagesize){
        logger.info("查询所有员工信息操作");
        HashMap map = new HashMap();
        // try {
            int pagenum_pagesize = (pagenum-1)*pagesize;
            List<Employee> list = employeeService.queryEmployeeDepartment();
            Employee[] array = new Employee[pagesize];
            int len = list.size();
            int length;
            int lengthsub;
            if(pagenum == 1){
                if(len<pagesize){
                    length = len;
                }else{
                    length = pagesize;
                }
            }else {
                lengthsub = list.size() - pagenum_pagesize;
                if(lengthsub<pagesize) {
                    length = (lengthsub);
                }else {
                    length = pagesize;
                }
            }
            Employee e;
            for(int j = 0;j<length;j++){
                e = list.get(j+pagenum_pagesize);
                array[j] = e;
            }
            meta.setMsg("查询成功");
            meta.setStatus(200);
            map.put("meta",meta);
            map.put("total",len);
            map.put("data",array);
        return map;
    }

    /**
     *
     * @return 返回数据 meta
     */
    @GetMapping("/emp/queryEmployee")
    @ResponseBody
    public HashMap queryEmployee(){
        HashMap hashMap = new HashMap();
        try {
            hashMap.put("data",employeeService.queryEmployeeDepartment());
            meta.setStatus(200);
            meta.setMsg("查询员工成功");
        }catch (Exception e){
            meta.setStatus(500);
            meta.setMsg("错误");
        }
        hashMap.put("meta",meta);
        return hashMap;
    }

    /**
     *
     * @return 返回数据 meta
     */
    @PostMapping("/addressBook")
    @ResponseBody
    public HashMap getDepartmentAddressBook(@RequestBody Map map){
        logger.info("查询通讯录操作");
        HashMap hashMap = new HashMap();
        try {
            System.out.println("map:"+map);
            List<Employee> employees = employeeService.queryEmployeeAddressByDepartmentId(map);
            Integer total = employeeService.countDepartmentEmployee(map);
            hashMap.put("data",employees);
            meta.setStatus(200);
            meta.setMsg("查询员工成功");
            hashMap.put("total",total);
        }catch (Exception e){
            logger.error("查询通讯录异常");
            meta.setStatus(200);
            meta.setMsg("错误");
        }
        hashMap.put("meta",meta);
        return hashMap;
    }

    /**
     *
     * @return 返回数据 meta
     */
    @GetMapping("/emp/queryEmployeeByDepartmentId")
    @ResponseBody
    public HashMap queryEmployeeByDepartmentId(@RequestParam("departmentId") Integer departmentId){
        logger.info("查询某部门下的员工操作");
        HashMap hashMap = new HashMap();
        try {
            List<Employee> employees = employeeService.queryEmployeeByDepartmentId(departmentId);
            hashMap.put("data",employees);
            meta.setStatus(200);
            meta.setMsg("查询员工成功");
        }catch (Exception e){
            meta.setStatus(200);
            meta.setMsg("错误");
        }
        hashMap.put("meta",meta);
        return hashMap;
    }
    /**
     *
     * @param id 请求修改参数员工ID
     * @return 返回数据 meta,data
     */
    @GetMapping("/emp/updateEmployee")
    @ResponseBody
    public HashMap toupdate(@RequestParam("id")Integer id){
        logger.info("查询要修改的员工");
        HashMap hashMap = new HashMap();
        meta.setMsg("请求成功");
        meta.setStatus(200);
        hashMap.put("meta",meta);
        hashMap.put("data",employeeService.queryEmployeeById(id));
        return hashMap;
    }

    /**
     *
     * @param id 请求删除参数员工ID
     * @return 返回数据 meta
     */
    @DeleteMapping("/emp/deleteEmployee")
    @ResponseBody
    public HashMap delete(@RequestParam("id")Integer[] id){
        logger.info("删除员工");
        Employee employee = employeeService.queryEmployeeAllById(id[0]);
        String userPhone = employee.getUserPhone();
        User user = userService.queryUserByMobile(userPhone);
        userService.deleteUserRole(user.getUserId());
        employeeService.deleteEmployee(id);
        HashMap hashMap = new HashMap();
        meta.setMsg("成功删除员工");
        meta.setStatus(200);
        hashMap.put("meta",meta);
        return hashMap;
    }

    /**
     *
     * @param map 请求添加参数Map
     * @return 返回数据 meta
     */
    @PostMapping("/emp/addEmployee")
    @ResponseBody
    public  HashMap add(@RequestBody Map map){
        logger.info("增加员工");
        HashMap hashMap = new HashMap();
        Role role = roleService.queryRoleByName(map.get("roleName").toString());
        // userService.updateUserPhone(map);
        User user = userService.queryUserByMobile(map.get("userPhone").toString());
        if(user == null){
            meta.setStatus(200);
            meta.setMsg("无对应电话 的用户");
            hashMap.put("meta",meta);
            return hashMap;
        }
        employeeService.addEmployee(map);
        Map hashmap2 = new HashMap();
        hashmap2.put("userIdTwo",user.getUserId());
        hashmap2.put("roleIdOne",role.getRoleId());
        hashmap2.put("userIdOne",user.getUserId());
        hashmap2.put("employeeIdOne",map.get("employeeId"));
        userService.userAddRole(hashmap2);
        employeeService.addUserEmployee(hashmap2);
        meta.setStatus(200);
        meta.setMsg("添加成功");
        hashMap.put("meta",meta);
        return hashMap;
    }

    /**
     *
     * @param map 请求修改参数Map
     * @return 返回数据 meta
     */
    @PostMapping("/emp/toupdate")
    @ResponseBody
    public  HashMap update(@RequestBody Map map){
        logger.info("修改员工");
        Role role = roleService.queryRoleByName(map.get("roleName").toString());
        // String userName = map.get("employeeName").toString();
        User user = userService.queryUserByMobile(map.get("userPhone").toString());
        employeeService.updateEmployee(map);
        Map hashmap2 = new HashMap();
        // hashmap2.put("userIdTwo",map.get("userId"));
        hashmap2.put("userIdTwo",user.getUserId());
        hashmap2.put("roleIdOne",role.getRoleId());
        System.out.println("roleIdOne:"+role.getRoleId());
        System.out.println("userIdTwo:"+user.getUserId());
        userService.updateUserRole(hashmap2);
        HashMap hashMap = new HashMap();
        meta.setStatus(200);
        meta.setMsg("修改成功");
        hashMap.put("meta",meta);
        return hashMap;
    }

}
