package com.yangyang.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

public class ShiroUtile {

    public static Subject login(String username,String password,String configName){
        SecurityManager manager = new IniSecurityManagerFactory("classpath:"+configName).getInstance();
        SecurityUtils.setSecurityManager(manager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try {
            subject.login(token);
            return subject;
        } catch (UnknownAccountException e) {
            System.err.println("用户名不存在!");
        }catch (IncorrectCredentialsException e){
            System.err.println("密码不正确!");
        }
        return null;
    }
    public static Subject login(String username, String password){
        return login(username,password,"classpath:shiro.ini");
    }
}
