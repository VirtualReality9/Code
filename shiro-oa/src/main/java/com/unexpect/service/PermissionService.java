package com.unexpect.service;


import com.unexpect.pojo.NavigationBar;
import com.unexpect.pojo.Permission;

import java.util.List;

public interface PermissionService {

    public List<Permission> getPermissions();

    public List<NavigationBar> getNavigationBars();

}
