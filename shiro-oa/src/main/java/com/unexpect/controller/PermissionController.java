package com.unexpect.controller;

import com.unexpect.pojo.Meta;
import com.unexpect.pojo.NavigationBar;
import com.unexpect.pojo.Permission;
import com.unexpect.service.PermissionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Controller
public class PermissionController {

    @Resource
    PermissionService permissionService;

    private static Logger logger= LogManager.getLogger(PermissionController.class);

    Meta meta = new Meta();

    @GetMapping("/pem/queryPermission")
    @ResponseBody
    public HashMap queryPermission(){
        logger.info("查询权限");
        List<Permission> permissions = permissionService.getPermissions();
        List<NavigationBar> navigationBarList = permissionService.getNavigationBars();
        HashMap hashMap = new HashMap();
        hashMap.put("data",permissions);
        hashMap.put("navigationBarList",navigationBarList);
        meta.setMsg("查询权限成功");
        meta.setStatus(200);
        hashMap.put("meta",meta);
        return hashMap;
    }

    @GetMapping("/permission/toadd")
    public String toadd(){
        return "Permission/toadd";
    }

    @GetMapping("/permission/toupdate")
    public String toupdate(){
        return "permission/update";
    }

    @GetMapping("/permission/delete")
    public String delete(){
        return "redirect:/permission/tomain";
    }

    @PostMapping("/permission/add")
    public  String add(){
        return "redirect:/permission/tomain";
    }

    @PostMapping("/permission/update")
    public  String update(){
        return "redirect:/permission/tomain";
    }

}
