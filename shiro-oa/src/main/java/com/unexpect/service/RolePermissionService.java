package com.unexpect.service;



import com.unexpect.pojo.RolePermission;

import java.util.List;
import java.util.Map;

public interface RolePermissionService {

    //查询角色权限
    public List<RolePermission> queryRolePermission();
    //增加角色权限
    public int addRolePermission(Map<String, Object> map);
    //修改角色权限
    public int updateRolePermission(Map<String, Object> map);
    //删除角色权限
    public int deleteRolePermission(Integer id);

    public int deleteRoleNavigationBar(Integer id);

    public Integer addRoleNavigationBar(Map map);

}
