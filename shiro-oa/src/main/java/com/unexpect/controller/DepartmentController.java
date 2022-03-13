package com.unexpect.controller;

import com.unexpect.pojo.Department;
import com.unexpect.pojo.Document;
import com.unexpect.pojo.Meta;
import com.unexpect.pojo.User;
import com.unexpect.service.DepartmentService;
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
public class DepartmentController {

    Meta meta = new Meta();
    @Resource
    DepartmentService departmentService;
    @Resource
    UserService userService;
    @Resource
    RoleService roleService;
    @Resource
    EmployeeService employeeService;

    private static Logger logger= LogManager.getLogger(DepartmentController.class);

    /**
     *查询手下员工
     * @param map
     * @return
     */
    @PostMapping("/dep/queryEmployeeByRoleLevel")
    @ResponseBody
    public HashMap queryDepartment(@RequestBody Map map) {
        logger.info("查询手下员工");
        HashMap hashMap = new HashMap();
        // try {
        List<Department> departments = departmentService.queryEmployeeByRoleLevel(map);
        hashMap.put("data", departments);
        logger.info("查询部门成功");
        meta.setMsg("查询成功");
        meta.setStatus(200);
        hashMap.put("meta", meta);
        return hashMap;
    }
    /**
     *
     * @return 返回每个部门的员工
     */
    @GetMapping("/dep/queryDepartmentEmployee")
    @ResponseBody
    public HashMap queryDepartmentEmployee(@RequestParam("employeeId") Integer employeeId){
        HashMap hashMap= new HashMap();
        try {
            List<Department> departments = departmentService.queryDepartmentEmployee(employeeId);
            hashMap.put("data",departments);
            meta.setMsg("查询成功");
            meta.setStatus(200);
            hashMap.put("meta",meta);
        }catch (Exception e){
            logger.error("查询异常");
            meta.setMsg("查询失败");
            meta.setStatus(500);
            hashMap.put("meta",meta);
            return hashMap;
        }

        return hashMap;
    }

    /**
     *
     * @param id 查询的部门ID
     * @param query 查询参数
     * @param pagenum 页号
     * @param pagesize 页大小
     * @return 返回数据
     */
    @GetMapping("/dep/queryDepartment")
    @ResponseBody
    public HashMap queryDepartment(@RequestParam(value = "id",required = false)Integer id,@RequestParam(value = "query",required = false)String query, @RequestParam(value = "pagenum",required = false)Integer pagenum, @RequestParam(value = "pagesize",required = false)Integer pagesize){
        logger.info("查询部门");
        HashMap map = new HashMap();
        if (pagenum != null){
            try {
                int pagenum_pagesize = (pagenum - 1) * pagesize;
                List<Department> list = departmentService.queryDepartment(id);
                Department[] array = new Department[pagesize];
                int len = list.size();
                int length;
                int lengthsub;
                if (pagenum == 1) {
                    if (len < pagesize) {
                        length = len;
                    } else {
                        length = pagesize;
                    }
                } else {
                    lengthsub = list.size() - pagenum_pagesize;
                    if (lengthsub < pagesize) {
                        length = (lengthsub);
                    } else {
                        length = pagesize;
                    }
                }
                Department d;
                for (int j = 0; j < length; j++) {
                    d = list.get(j + pagenum_pagesize);
                    array[j] = d;
                }
                meta.setMsg("查询成功");
                meta.setStatus(200);
                map.put("meta", meta);
                map.put("total", len);
                map.put("data", array);
            } catch (Exception e) {
                logger.error("查询错误！！！");
            }
        }else {
            try {
                List<Department> list = departmentService.queryDepartment(id);
                meta.setMsg("查询成功");
                meta.setStatus(200);
                map.put("meta", meta);
                map.put("data", list);
            }catch (Exception e){
                logger.info("查询异常");
                meta.setMsg("查询失败");
                meta.setStatus(500);
                map.put("meta", meta);
                return  map;
            }
        }

        System.out.println("map:"+map);
        return map;
    }

    /**
     *
     * @param map 请求参数map
     * @return 返回数据
     */
    @PostMapping("/dep/addDepartment")
    @ResponseBody
    public HashMap addDepartment(@RequestBody Map map){
        logger.info("增加部门");
        HashMap hashMap= new HashMap();
        try {
            User user = userService.queryUserByMobile(map.get("userPhone").toString());
            HashMap hashmap2 = new HashMap();
            System.out.println("userId:"+user.getUserId());
            hashmap2.put("userIdTwo",user.getUserId());
            hashmap2.put("roleIdOne",3);
            userService.deleteUserRole(user.getUserId());
            userService.userAddRole(hashmap2);
            departmentService.addDepartment(map);
            meta.setMsg("添加成功");
            meta.setStatus(200);
        }catch (Exception e){
            logger.error("添加异常");
            meta.setMsg("添加失败");
            meta.setStatus(500);
        }

        hashMap.put("meta",meta);
        return hashMap;
    }

    /**
     *
     * @param id 修改参数部门ID
     * @return 返回数据
     */
    @GetMapping("/dep/updateDepartment")
    @ResponseBody
    public HashMap toupdate(@RequestParam("id")Integer id){
        logger.info("查询要修改的部门");
        HashMap hashMap = new HashMap();
        try {
            meta.setMsg("请求成功");
            meta.setStatus(200);
            hashMap.put("meta", meta);
            hashMap.put("data", departmentService.queryDepartmentById(id));
        }catch (Exception e){
            logger.error("查询异常");
            meta.setMsg("请求失败");
            meta.setStatus(500);
            hashMap.put("meta", meta);
        }
        return hashMap;
    }

    /**
     *
     * @param map 请求参数map
     * @return 返回数据
     */
    @PostMapping("/dep/toupdate")
    @ResponseBody
    public HashMap updateDepartment(@RequestBody Map map){
        logger.info("修改部门");
        HashMap hashMap= new HashMap();
        String userName1 = map.get("departmentManager2").toString();
        String userName2 = map.get("departmentManager").toString();
        try {
            if (userName1.equals(userName2)) {
                departmentService.updateDepartment(map);
            } else {
                User user1 = userService.queryUserByUserName(userName1);
                User user2 = userService.queryUserByUserName(userName2);
                HashMap hashmap2 = new HashMap();
                hashmap2.put("userIdTwo", user2.getUserId());
                hashmap2.put("roleIdOne", roleService.queryRoleByName("部门经理").getRoleId());
                userService.deleteUserRole(user2.getUserId());
                userService.userAddRole(hashmap2);
                hashmap2.put("userIdTwo", user1.getUserId());
                // Role role = roleService.queryRoleByName("普通员工");
                // hashmap2.put("roleIdOne",role.getRoleId());
                hashmap2.put("roleIdOne", roleService.queryRoleByName("普通员工").getRoleId());
                userService.deleteUserRole(user1.getUserId());
                userService.userAddRole(hashmap2);
            }
            departmentService.updateDepartment(map);
            meta.setMsg("修改成功");
            meta.setStatus(200);
            hashMap.put("meta", meta);
        }catch (Exception e){
            logger.error("修改异常");
            meta.setMsg("修改异常");
            meta.setStatus(500);
            hashMap.put("meta", meta);
        }
        return hashMap;
    }

    /**
     *
     * @param id 请求参数部门ID
     * @return 返回数据
     */
    @GetMapping("/dep/deleteDepartment")
    @ResponseBody
    public HashMap todelete(@RequestParam("id")Integer id){
        logger.info("删除部门");
        HashMap hashMap= new HashMap();
        try {
            List<Department> departments = departmentService.queryDepartment(id);
            meta.setMsg("查询其它部门成功");
            meta.setStatus(500);
            hashMap.put("meta",meta);
            hashMap.put("data",departments);
        }catch (Exception e){
            logger.error("查询部门异常");
            meta.setMsg("查询部门失败");
            meta.setStatus(500);
            hashMap.put("meta",meta);
        }
        return hashMap;
    }

    /**
     *
     * @param map 请求参数 map
     * @return 返回数据
     */
    @PostMapping("/dep/todelete")
    @ResponseBody
    public HashMap deleteDepartment(@RequestBody Map map){
        logger.info("删除部门");
        HashMap hashMap= new HashMap();
        try {
            Integer departmentId1 = Integer.parseInt(map.get("departmentNewId").toString());
            Integer departmentId2 =  Integer.parseInt(map.get("departmentOldId").toString());
            String departmentManager = map.get("departmentManager").toString();
            departmentService.deleteDepartment(departmentId2);
            User user = userService.queryUserByUserName(departmentManager);
            HashMap hashmap2 = new HashMap();
            hashmap2.put("userIdTwo", user.getUserId());
            // hashmap2.put("roleIdOne",Integer.parseInt(map.get("roleId").toString()));
            hashmap2.put("roleIdOne", roleService.queryRoleByName("小组长").getRoleId());
            userService.deleteUserRole(user.getUserId());
            userService.userAddRole(hashmap2);
            hashmap2.put("newDepartmentId",departmentId1);
            hashmap2.put("departmentId",departmentId2);
            employeeService.updateEmployeeDepartment(hashmap2);
            meta.setMsg("删除成功");
            meta.setStatus(200);
        }catch (Exception e){
            logger.error("删除异常");
            meta.setMsg("删除错误");
            meta.setStatus(500);
            hashMap.put("meta",meta);
            return hashMap;
        }

        hashMap.put("meta",meta);
        return hashMap;
    }
}
