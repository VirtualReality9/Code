package com.unexpect.mapper;

import com.unexpect.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface RoleMapper {
    //查询Role
    public List<Role> queryRole();
    //通过roleId查询Role
    public Role queryRoleById(Integer roleId);
    //通过roleName查询Role
    public Role queryRoleByName(String roleName);
    //增加Role
    public int addRole(Map<String, Object> roleMap);
    //修改Role
    public int updateRole(Map<String, Object> roleMap);
    //删除Role
    public int deleteRole(Integer[] roleId);


//    public int addRolePermission(Map<String, Object> rolePermissionMap);
//
//    public Role queryRole(Map<String, Object> roleMap);
}
