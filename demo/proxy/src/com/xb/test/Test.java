package com.xb.test;

import com.xb.service.UserService;
import com.xb.service.impl.UserServiceImpl;
import com.xb.utils.ProxyInvocationHandler;

public class Test {
    public static void main(String[] args) {
        // 真实对象，无论如何，真实对象都必须存在
        UserService userService = new UserServiceImpl();
        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        /**
         * 动态代理
         */
        // 注入真实对象
        pih.setTarget(userService);
        // 获取代理对象
        UserService proxy = (UserService) pih.getProxy();
        proxy.rent();
    }
}
