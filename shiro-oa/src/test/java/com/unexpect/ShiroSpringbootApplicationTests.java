package com.unexpect;

// import com.unexpect.Utils.RedisUtil;
import com.unexpect.service.Impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

@SpringBootTest
class ShiroSpringbootApplicationTests {

    @Autowired
    UserServiceImpl userService;

    // @Resource
    // RedisUtil redisUtil;

    @Test
    void contextLoads() throws SQLException {
        System.out.println(userService.queryUserByName("unexpect"));
    }

    @Test
    void contextLoads2() throws SQLException {
        // redisUtil.set("key1","values");
        // System.out.println(redisUtil.get("key1"));
    }

}
