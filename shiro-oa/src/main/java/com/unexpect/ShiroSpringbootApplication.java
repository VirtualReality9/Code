package com.unexpect;

import com.unexpect.utils.HtmlToPdfUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class ShiroSpringbootApplication {

    public static void main(String[] args) {
        HtmlToPdfUtils htmlToPdfUtils = new HtmlToPdfUtils();
        SpringApplication.run(ShiroSpringbootApplication.class, args);
        File tmpFile = new File(htmlToPdfUtils.getAttachmentAbsolutePath());
        System.out.println("tmpFile:"+tmpFile);
        //判断是否存在该文件夹，若不存在则创建文件夹
        if(!tmpFile.exists()){
            System.out.println("创建文件夹===");
            tmpFile.mkdirs();
        }
    }

}
