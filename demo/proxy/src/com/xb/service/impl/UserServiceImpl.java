package com.xb.service.impl;

import com.xb.service.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public void rent() {
        System.out.println("房东要出租房子");
    }
}
