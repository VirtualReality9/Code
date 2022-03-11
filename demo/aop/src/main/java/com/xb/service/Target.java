package com.xb.service;

import org.springframework.stereotype.Service;

@Service
public class Target implements TargetInterface{
    @Override
    public void save() {
        System.out.println("save run ...");
    }
}
