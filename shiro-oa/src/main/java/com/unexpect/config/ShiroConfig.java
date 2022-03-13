package com.unexpect.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.unexpect.config.Cache.RedisCacheManager;
import com.unexpect.config.Realms.CustomerMd5Realm;
import com.unexpect.pojo.Permission;
import com.unexpect.service.PermissionService;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Resource
    PermissionService permissionService;

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //给filter设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        //登录拦截
        Map<String,String> filterMap = new LinkedHashMap<>();
        //拦截
        List<Permission> perms = permissionService.getPermissions();
        for(Permission perm :perms){
            String permUrl = perm.getPermissionUrl();
            String permN = perm.getKeyword();
            filterMap.put(permUrl,"perms["+permN+"]");
        }
        //设置登出
        filterMap.put("/pem/queryPermission","authc");
        filterMap.put("/dep/queryEmployeeByRoleLevel","authc");
        filterMap.put("/logout","logout");
        filterMap.put("/retrievePassword","anon");
        filterMap.put("/register","anon");
        bean.setFilterChainDefinitionMap(filterMap);
        //设置登录的请求
        bean.setLoginUrl("/toLogin");
        //未授权页面
        bean.setUnauthorizedUrl("/noauth");
        return bean;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultSecurityManager(@Qualifier("userRealm") Realm userRealm){
        //创建SecurityManager并将自定义Realm注入
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 给安全管理器
        // 关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    @Bean
    public Realm userRealm(){
        CustomerMd5Realm customerMd5Realm = new CustomerMd5Realm();
        //修改凭证校验匹配器
        //设置realm使用hash凭证匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //使用算法
        credentialsMatcher.setHashAlgorithmName("md5");
        //设置散列次数
        credentialsMatcher.setHashIterations(1024);
        customerMd5Realm.setCredentialsMatcher(credentialsMatcher);
        return customerMd5Realm;
    }

    //整合ShiroDialect getShiroDialect
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
