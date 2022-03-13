/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: test
 * Author:   tjqwecom
 * Date:     2020-11-13 22:21
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.unexpect;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author tjqwecom
 * @create 2020-11-13
 * @since 1.0.0
 */

public class test {
    public static void main(String[] arg){

        StandardPBEStringEncryptor standardPBEStringEncryptor =new StandardPBEStringEncryptor();
        /*配置文件中配置如下的算法*/
        standardPBEStringEncryptor.setAlgorithm("PBEWithMD5AndDES");
        /*配置文件中配置的password*/
        standardPBEStringEncryptor.setPassword("EWRREWRERWECCCXC");
        /*要加密的文本*/
        String name = standardPBEStringEncryptor.encrypt("root");
        String password = standardPBEStringEncryptor.encrypt("Wodeadcarry666");
        /*将加密的文本写到配置文件中*/
        System.out.println("name="+name);
        System.out.println("password="+password);
        //879d6457d5315e047d842e5507c262b5
//        System.out.println(test1.md5Encryption("111111","2b0c9803-5c9d-4589-b228-dc2c36c7"));
    }
}
