package com.unexpect.service.Impl;

import com.unexpect.mapper.UserMapper;
import com.unexpect.pojo.Permission;
import com.unexpect.pojo.User;
import com.unexpect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User queryUserByName(String userPhone) {
        return userMapper.queryUserByName(userPhone);
    }

    @Override
    public List<User> queryUser() {
        return userMapper.queryUser();
    }

    @Override
    public User queryUserById(Integer id) {
        return userMapper.queryUserById(id);
    }

    @Override
    public Permission queryPermissionById(Integer id) {
        return userMapper.queryPermissionById(id);
    }

    @Override
    public User queryUserByMobile(String mobile) {
        return userMapper.queryUserByMobile(mobile);
    }

    @Override
    public int addUser(Map<String, Object> userMap) {

        userMapper.addUser(userMap);
        return 0;
    }

    @Override
    public int updateUser(Map<String, Object> userMap) {
        userMapper.updateUser(userMap);
        return 0;
    }

    @Override
    public int deleteUser(Integer[] userId) {
        userMapper.deleteUser(userId);
        return 0;
    }

    @Override
    public void userAddRole(Map map) {
        userMapper.userAddRole(map);
    }

    @Override
    public void updateUserRole(Map map) {
        userMapper.updateUserRole(map);
    }

    @Override
    public void deleteUserRole(Integer id) {
        userMapper.deleteUserRole(id);
    }

    @Override
    public void updateUserPhone(Map map) {

    }

    @Override
    public User queryPermissions(String mobile) {
        return userMapper.queryPermissions(mobile);
    }

    @Override
    public User queryNavigationBars(String mobile) {
        return userMapper.queryNavigationBars(mobile);
    }

    @Override
    public User queryManagerNavigationBars(Integer userId) {
        return userMapper.queryManagerNavigationBars(userId);
    }

    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }

    @Override
    public User queryUserByUserName(String userName) {
        return userMapper.queryUserByUserName(userName);
    }

    @Override
    public User queryUserAllByUserPhone(String userPhone) {
        return userMapper.queryUserAllByUserPhone(userPhone);
    }

}
