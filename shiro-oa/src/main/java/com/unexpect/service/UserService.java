package com.unexpect.service;

import com.unexpect.pojo.Employee;
import com.unexpect.pojo.Permission;
import com.unexpect.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    public User queryUserByName(String userPhone);

    public List<User> queryUser();

    public User queryUserById(Integer id);

    public Permission queryPermissionById(Integer id);

    //通过手机号查询User
    public User queryUserByMobile(String mobile);
    //增加User
    public int addUser(Map<String, Object> userMap);
    //修改User
    public int updateUser(Map<String, Object> userMap);
    //删除User
    public int deleteUser(Integer[] userId);
    //添加UserRole
    public void userAddRole(Map map);
    //修改UserRole
    public void updateUserRole(Map map);
    //删除UserRole
    public void deleteUserRole(Integer id);
    //改User手机号
    public void updateUserPhone(Map map);

    //查询权限
    public User queryPermissions(String mobile);
    //查询菜单
    public User queryNavigationBars(String mobile);
    //查询后台菜单
    public User queryManagerNavigationBars(Integer userId);
    //查询全部菜单
    public List<User> getAll();

    public User queryUserByUserName(String userName);

    public User queryUserAllByUserPhone(String userPhone);
}
