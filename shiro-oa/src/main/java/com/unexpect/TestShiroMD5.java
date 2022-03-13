package com.unexpect;

import com.unexpect.config.Realms.CustomerMd5Realm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

import java.util.Arrays;

public class TestShiroMD5 {

    public static void main(String[] args){

        // //创建一个md5算法
        // Md5Hash md5Hash = new Md5Hash();
        //
        // md5Hash.setBytes("123".getBytes());
        //
        // String s = md5Hash.toHex();
        // System.out.println(s);

        //使用md5
        // Md5Hash md5Hash = new Md5Hash("123");
        // System.out.println(md5Hash.toHex());

        // //使用MD5 + salt处理
        // Md5Hash md5Hash1 = new Md5Hash("123","X0*7ps");
        // System.out.println(md5Hash1);

        // 使用MD5 + salt + hash 散列
        Md5Hash md5Hash2 = new Md5Hash("123456","^p!Qtp7t",1024);
        System.out.println(md5Hash2);

        //创建安全管理器
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        //注入realm
        CustomerMd5Realm customerMd5Realm = new CustomerMd5Realm();
        //设置realm使用hash凭证匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //使用算法
        credentialsMatcher.setHashAlgorithmName("md5");
        credentialsMatcher.setHashIterations(1024);//设置散列次数
        customerMd5Realm.setCredentialsMatcher(credentialsMatcher);
        securityManager.setRealm(customerMd5Realm);
        //将安全管理器注入安全工具
        SecurityUtils.setSecurityManager(securityManager);
        //通过安全工具类获取subject
        Subject subject = SecurityUtils.getSubject();
        //认证
        UsernamePasswordToken token = new UsernamePasswordToken("^p!Qtp7t","123");

        try {
            subject.login(token);
            System.out.println("登录成功");
        }catch (UnknownAccountException e){
            System.out.println("用户名错误");
        }catch (IncorrectCredentialsException e){
            System.out.println("密码错误");
        }

        //认证用户进行授权
        if(subject.isAuthenticated()){


            //基于角色的权限控制
            System.out.println(subject.hasRole("admin"));

            //基于多角色权限控制
            assert subject.hasAllRoles(Arrays.asList("admin","user"));

            //是否具有其中一个角色
            boolean[] booleans = subject.hasRoles(Arrays.asList("admin","super","userx"));
            for (boolean aBoolean :booleans){
                System.out.println(aBoolean);
            }

            System.out.println("=================");
            //基于权限字符串的访问控制 资源标识符：操作：资源类型
            System.out.println("权限"+subject.isPermitted("user:*:*"));

            //分别具有哪些权限
            boolean[] permitted = subject.isPermitted("","");
            for (boolean aBoolean :permitted){
                System.out.println(aBoolean);
            }

            boolean permittedAll = subject.isPermittedAll("","");
            System.out.println(permittedAll);
        }
    }

}
