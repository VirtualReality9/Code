package com.unexpect.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    // 在D:/res/pic下如果有一张 luffy.jpg.jpg的图片，那么：
    // 1 访问：http://localhost:8080/img/luffy.jpg 可以访问到
    // 2 html 中 <img src="http://localhost:8080/upload/pdf/luffy.jpg">
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/pdf/**").addResourceLocations("file:C:/upload/pdf/");
        registry.addResourceHandler("/upload/attachment/**").addResourceLocations("file:C:/upload/attachment/");
    }
}