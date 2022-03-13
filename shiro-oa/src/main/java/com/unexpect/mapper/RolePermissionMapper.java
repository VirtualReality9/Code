package com.unexpect.mapper;

import com.unexpect.pojo.RolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface RolePermissionMapper {

    //查询角色权限
    public List<RolePermission> queryRolePermission();
    //增加角色权限
    public int addRolePermission(Map<String, Object> map);
    //修改角色权限
    public int updateRolePermission(Map<String, Object> map);
    //删除角色权限
    public int deleteRolePermission(Integer id);
    //删除角色功能
    public int deleteRoleNavigationBar(Integer id);
    //添加角色功能
    public Integer addRoleNavigationBar(Map map);

}
