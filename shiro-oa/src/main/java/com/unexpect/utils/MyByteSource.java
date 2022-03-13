package com.unexpect.utils;

import org.apache.shiro.util.SimpleByteSource;

import java.io.Serializable;

//自定义盐实现序列化接口
public class MyByteSource extends SimpleByteSource implements Serializable {

    public MyByteSource(String string) {
        super(string);
    }
}
