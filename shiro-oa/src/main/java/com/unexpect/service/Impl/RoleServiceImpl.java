package com.unexpect.service.Impl;

import com.unexpect.mapper.RoleMapper;
import com.unexpect.pojo.Role;
import com.unexpect.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    RoleMapper roleMapper;

    @Override
    public List<Role> queryRole() {
        return roleMapper.queryRole();
    }

    @Override
    public Role queryRoleById(Integer roleId) {
        return roleMapper.queryRoleById(roleId);
    }

    @Override
    public Role queryRoleByName(String roleName) {
        return roleMapper.queryRoleByName(roleName);
    }

    @Override
    public int addRole(Map<String, Object> roleMap) {
        return roleMapper.addRole(roleMap);
    }

    @Override
    public int updateRole(Map<String, Object> roleMap) {
        return roleMapper.updateRole(roleMap);
    }

    @Override
    public int deleteRole(Integer[] roleId) {
        return roleMapper.deleteRole(roleId);
    }
}
