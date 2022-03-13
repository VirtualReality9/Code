package com.unexpect.service.Impl;

import com.unexpect.mapper.PermissionMapper;
import com.unexpect.pojo.NavigationBar;
import com.unexpect.pojo.Permission;
import com.unexpect.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public List<Permission> getPermissions() {
        return permissionMapper.getPermissions();
    }

    @Override
    public List<NavigationBar> getNavigationBars() {
        return permissionMapper.getNavigationBars();
    }
}
