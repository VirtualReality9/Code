package com.unexpect.controller;


import com.unexpect.pojo.Meta;
import com.unexpect.pojo.Role;
import com.unexpect.service.RolePermissionService;
import com.unexpect.service.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RoleController {

    Meta meta = new Meta();

    @Resource
    RoleService roleService;
    @Resource
    RolePermissionService rolePermissionService;

    private static Logger logger= LogManager.getLogger(RoleController.class);

    //Role查询
    /**
     *
     * @return 返回数据 meta,data
     */
    @GetMapping("/role/queryRole")
    @ResponseBody
    public HashMap queryRole(){
        logger.info("查询角色");
        HashMap hashMap = new HashMap();
        hashMap.put("data",roleService.queryRole());
        meta.setStatus(200);
        meta.setMsg("查询成功");
        hashMap.put("meta",meta);
        return hashMap;
    }

    /**
     *
     * @param query query
     * @param pagenum 页号
     * @param pagesize 页大小
     * @return 返回数据 total,meta,data
     */
    @GetMapping("/role/queryRoleList")
    @ResponseBody
    public HashMap queryRoleList(@RequestParam(value = "query",required = false)String query, @RequestParam("pagenum")Integer pagenum, @RequestParam("pagesize")Integer pagesize){
        logger.info("分页查询角色");
        HashMap map = new HashMap();
        try {
            int pagenum_pagesize = (pagenum-1)*pagesize;
            List<Role> list = roleService.queryRole();
            Role[] array = new Role[pagesize];
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
            Role e;
            for(int j = 0;j<length;j++){
                e = list.get(j+pagenum_pagesize);
                array[j] = e;
            }
            meta.setMsg("查询成功");
            meta.setStatus(200);
            map.put("meta",meta);
            map.put("total",len);
            map.put("data",array);
        }catch (Exception e){
            logger.error("分页查询异常");
        }
        System.out.println("map:"+map);
        return map;
    }

    //Role添加

    /**
     *
     * @param map 请求添加参数 map
     * @return 返回数据 meta
     */
    @PostMapping("/role/addRole")
    @ResponseBody
    public HashMap add(@RequestBody  Map map){
        logger.info("添加角色");
        HashMap hashMap = new HashMap();
        ArrayList<Integer> permissionId = (ArrayList<Integer>) map.get("permissionId");
        ArrayList<Integer> nbid = (ArrayList<Integer>) map.get("nbid");
        System.out.println("nbid:"+nbid);
        System.out.println("permissionId:"+permissionId);
        HashMap hashMap2 = new HashMap();

        System.out.println("addrole");
        roleService.addRole(map);
        hashMap2.put("roleId",map.get("roleId"));
        for(int i = 0;i<permissionId.size();i++){
            hashMap2.put("permissionId",permissionId.get(i));
            hashMap2.put("nbid",nbid.get(i));
            rolePermissionService.addRolePermission(hashMap2);
            rolePermissionService.addRoleNavigationBar(hashMap2);
        }
        meta.setStatus(200);
        meta.setMsg("添加角色成功");
        hashMap.put("meta",meta);
        return hashMap;
    }

    //Role修改

    /**
     *
     * @param id 请求修改数据roleId
     * @return 返回数据 meta
     */
    @GetMapping("/role/updateRole")
    @ResponseBody
    public HashMap toupdate(@RequestParam("id")Integer id){
        logger.info("查询要修改的角色");
        HashMap hashMap = new HashMap();
        try {
            hashMap.put("data",roleService.queryRoleById(id));
            meta.setMsg("请求成功");
            meta.setStatus(200);
        }catch (Exception e){
            logger.error("查询修改的角色异常");
            meta.setMsg("错误");
            meta.setStatus(500);
        }
        hashMap.put("meta",meta);
        return hashMap;
    }

    /**
     *
     * @param map 请求修改数据 Map
     * @return 返回数据 meta
     */
    @PostMapping("/role/toupdate")
    @ResponseBody
    public  HashMap update(@RequestBody Map map){
        logger.info("修改角色");
        HashMap hashMap = new HashMap();
        ArrayList<Integer> permissionId = (ArrayList<Integer>) map.get("permissionId");
        ArrayList<Integer> nbid = (ArrayList<Integer>) map.get("nbid");
        System.out.println("nbid:"+nbid);
        System.out.println("permissionId:"+permissionId);
        HashMap hashMap2 = new HashMap();
        roleService.updateRole(map);
        rolePermissionService.deleteRolePermission(Integer.parseInt(map.get("roleId").toString()));
        rolePermissionService.deleteRoleNavigationBar(Integer.parseInt(map.get("roleId").toString()));
        hashMap2.put("roleId",map.get("roleId"));
        for(int i = 0;i<permissionId.size();i++){
            hashMap2.put("permissionId",permissionId.get(i));
            hashMap2.put("nbid",nbid.get(i));
            rolePermissionService.addRolePermission(hashMap2);
            rolePermissionService.addRoleNavigationBar(hashMap2);
        }
        meta.setStatus(200);
        meta.setMsg("修改角色成功");
        hashMap.put("meta",meta);
        return hashMap;
    }

    //Role删除

    /**
     *
     * @param id 请求删除参数roleId
     * @return 返回数据 meta
     */
    @DeleteMapping("/role/deleteRole")
    @ResponseBody
    public HashMap delete(@RequestParam("id")Integer[] id){
        logger.info("删除角色");
        HashMap hashMap = new HashMap();
        try {
            roleService.deleteRole(id);
            rolePermissionService.deleteRolePermission(id[0]);
            meta.setMsg("成功删除角色");
            meta.setStatus(200);
        }catch (Exception e){
            logger.error("删除角色异常");
            meta.setMsg("错误");
            meta.setStatus(500);
        }
        hashMap.put("meta",meta);
        return hashMap;
    }
}
