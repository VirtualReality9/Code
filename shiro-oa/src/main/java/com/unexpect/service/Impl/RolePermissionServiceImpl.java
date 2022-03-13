package com.unexpect.service.Impl;

import com.unexpect.mapper.RolePermissionMapper;
import com.unexpect.pojo.RolePermission;
import com.unexpect.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    RolePermissionMapper rolePermissionMapper;

    @Override
    public List<RolePermission> queryRolePermission() {
        return rolePermissionMapper.queryRolePermission();
    }

    @Override
    public int addRolePermission(Map<String, Object> map) {
        return rolePermissionMapper.addRolePermission(map);
    }

    @Override
    public int updateRolePermission(Map<String, Object> map) {
        return rolePermissionMapper.updateRolePermission(map);
    }

    @Override
    public int deleteRolePermission(Integer id) {
        return rolePermissionMapper.deleteRolePermission(id);
    }

    @Override
    public int deleteRoleNavigationBar(Integer id) {
        return rolePermissionMapper.deleteRoleNavigationBar(id);
    }

    @Override
    public Integer addRoleNavigationBar(Map map) {
        return rolePermissionMapper.addRoleNavigationBar(map);
    }

}
