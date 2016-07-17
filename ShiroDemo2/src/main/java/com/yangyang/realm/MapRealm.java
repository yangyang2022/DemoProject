package com.yangyang.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

import java.util.HashMap;
import java.util.Map;

public class MapRealm implements Realm{

    private static Map<String,String> users;
    static {
        users = new HashMap<>();
        users.put("kh","111");
        users.put("zs","123");
        users.put("洋洋","222");
    }

    @Override
    public String getName() {
        return "mapRealm";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = token.getPrincipal().toString();
        String password = new String((char[])token.getCredentials());
        System.out.println(username+" : "+password);

        if(!users.containsKey(username)) throw new UnknownAccountException("用户名出错!");
        if(!users.get(username).equals(password)) throw new IncorrectCredentialsException("用户密码不正确!!");

        AuthenticationInfo info = new SimpleAuthenticationInfo(username,password,getName());

        return info;
    }
}
