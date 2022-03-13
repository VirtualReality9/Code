package com.unexpect.mapper;

import com.unexpect.pojo.NavigationBar;
import com.unexpect.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PermissionMapper {

    public List<Permission> getPermissions();

    public List<NavigationBar> getNavigationBars();

}
