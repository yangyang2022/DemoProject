package com.yangyang.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

//最好继承 AuthorizingRealm( 可以认证和授权 )
public class MyRealm extends AuthorizingRealm{
    //授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Object principal = principalCollection.getPrimaryPrincipal();
        if("admin".equals(principal)){
            info.addRole("admin");
        }
        info.addRole("user");

        return info;
    }

    //认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        // 1: 从token中获取登录的username!注意不需要获取password
        // 2: 利用username 查询数据库得到 用户信息
        // 3: 创建 SimpleAuthenticationInfo 对象并返回,注意该对象的凭证是从数据库获取
        //而不是页面输入,实际的密码校验是由shiro完成

        // 4: 关于密码加密-->
        // 4.1 加密方式 -> 在当前的realm中 创建setCredentialsMatcher方法,并用@PostConstruct
        // 4.2: 设置盐值 -> 一般从数据库中查询得到

        /**
         *  1: 编写表单 -> 表单的action和username和password的参数是什么?
         *  2: 提交表单 -> 提交到何处?例如提交到springmvc的handler
         *  3: Subject currentUser = SecurityUtils.getSubject()
         *    UsernamePasswordToken token = new UsernamePasswordToken(username,password);
         *    currentUser.login(token)
         *  4: 当Subject调用login方法时,会触发 doGetAuthenticationInfo 方法,并把UsernamePasswordToken
         *  传入,然后在该方法中执行真正的认证:访问数据库进行比对
         *
         *
         */

        //登录主要信息:可以是一个实体类对象,但该实体类一定是根据token的username查询得到的
        Object principal = token.getPrincipal();

        //认证信息:从数据库查询出来的信息,密码比对交给shiro比较
        String credentials = "123123";
        String realmName = this.getName();
        String source = "yangyang";
        ByteSource creditialSalt = new Md5Hash(source);
        SimpleAuthenticationInfo info  = new SimpleAuthenticationInfo(principal,credentials,creditialSalt,realmName);
        return info;
    }

    // 相当于 bean里面的 init-method
    public void setCredentialsMatcher(){
        HashedCredentialsMatcher credential = new HashedCredentialsMatcher();
        credential.setHashAlgorithmName("MD5");
        credential.setHashIterations(1024);
        setCredentialsMatcher(credential);
    }
}
