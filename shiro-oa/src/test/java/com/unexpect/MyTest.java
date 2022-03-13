package com.unexpect;

import com.alibaba.druid.filter.config.ConfigTools;
import com.unexpect.pojo.ToDoList;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Log4j2
public class MyTest {

    @Test
    void log4j2() throws Exception {
        String password = "root";
        System.out.println(password);
        String newPassword =  ConfigTools.encrypt(password);
        System.out.println(newPassword);
        String passwords =  ConfigTools.decrypt(newPassword);
        System.out.println(passwords);
        log.info("我是info信息");
        log.warn("我是warn信息");
        log.error("我是error信息");
        log.fatal("我是fatal信息");
    }
}
