package com.yangyang.realm;

import com.yangyang.permission.MyPermission;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class StaticRealm extends AuthorizingRealm{
    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole("r1");
        info.addRole("r2");

        info.addStringPermission("+user+*");
        info.addObjectPermission(new MyPermission("+topic+create"));
        info.addObjectPermission(new MyPermission("+topic+update"));

        return info;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = token.getPrincipal().toString();
        String password = new String((char[])token.getCredentials());

        if(!"kh".equals(username)) throw new UnauthenticatedException("用户名不存在!");
        if(!"123".equals(password)) throw new IncorrectCredentialsException("用户密码不正确!");

        return new SimpleAuthenticationInfo("yangyang@gmail.com",password,getName());
    }
}
