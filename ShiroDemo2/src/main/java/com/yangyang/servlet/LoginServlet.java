package com.yangyang.servlet;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet",urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        String msg = null;
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            msg = "用户名出错";
            System.err.println(" --> 登录失败 " +username+" : "+password);
        }catch (IncorrectCredentialsException e){
            msg = "用户密码不正确!";
        }catch (AuthenticationException e){
            msg = " 其他异常: "+e.getMessage();
        }
        if(msg != null){
            request.setAttribute("msg",msg);
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);
        }else{
            request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);

    }
}
