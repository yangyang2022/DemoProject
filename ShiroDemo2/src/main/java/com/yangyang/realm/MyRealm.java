package com.yangyang.realm;

import com.yangyang.DaoRepo.UserDao;
import com.yangyang.IService.IUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MyRealm extends AuthorizingRealm{

    @Resource
    private IUserService userService ;
    @Resource
    private UserDao userDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        String username = principals.getPrimaryPrincipal().toString();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        userService.getRoles(username).forEach(e->info.addRole(e));
        userService.getPerms(username).forEach(e->info.addStringPermission(e));

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = token.getPrincipal().toString();

        System.out.println(userService+" -- "+userDao);

        //User user = userService.getByUsername(username);
        //
        //if(user!=null){
        //    SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),getName());
        //    return info;
        //}
        return null;
    }

}
