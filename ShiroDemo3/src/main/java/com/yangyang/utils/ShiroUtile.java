package com.yangyang.utils;

import com.yangyang.model.shiroModel.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

public class ShiroUtile {

    private static String handlerToken(Subject subject,UsernamePasswordToken token){
        String errorMsg = null;
        try {
            subject.login(token);
            //return subject;
        } catch (UnknownAccountException e) {
            System.err.println("用户名不存在!");
            errorMsg = "用户名不存在!";
        }catch (IncorrectCredentialsException e){
            System.err.println("密码不正确!");
            errorMsg = "密码不正确!";
        }
        return errorMsg;
    }
    public static String login(String username,String password,String configName){
        SecurityManager manager = new IniSecurityManagerFactory("classpath:"+configName).getInstance();
        SecurityUtils.setSecurityManager(manager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        return handlerToken(subject,token);
    }
    public static String login(User user){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        return handlerToken(subject,token);
    }
}
