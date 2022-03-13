package com.unexpect.config.Realms;

import com.unexpect.pojo.Permission;
import com.unexpect.pojo.User;
import com.unexpect.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import com.unexpect.utils.MyByteSource;

import java.util.List;

/**自定义Realm 加入md5 + salt + hash 继承AuthorizingRealm
 *
 **/
public class CustomerMd5Realm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        User currentUser = userService.queryUserByName(primaryPrincipal);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        List<Permission> permissions = currentUser.getPermissions();
        for (Permission perm : permissions){
            //将权限字符串用addStringPermission方法授权
            String permStr = perm.getKeyword();
            simpleAuthorizationInfo.addStringPermission(permStr);
        }
        return simpleAuthorizationInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取身份信息
        String principal = (String) authenticationToken.getPrincipal();
        User user = userService.queryUserByMobile(principal);
        if(!ObjectUtils.isEmpty(user)){
            return new SimpleAuthenticationInfo(user.getUserPhone(),user.getUserPassword(),new MyByteSource(user.getSalt()),this.getName());
        }
        return null;
    }
}
