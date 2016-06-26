package com.yangyang.web;

import com.yangyang.dao.DaoFactory;
import com.yangyang.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebServlet(name = "BaseServlet")
public class BaseServlet extends HttpServlet {

    public static final String redirect="redirect:";
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DaoFactory.injectDao(this);//注入所有DAO
        String method = request.getParameter("method");
        if(method == null || "".equals(method)) method = "list";
        try {
            Method m = this.getClass().getMethod(method,HttpServletRequest.class,HttpServletResponse.class);
            //这里进行权限控制
            int flag = checkAuth(request,response,m);
            if(flag == 1) {
                response.sendRedirect("user?method=loginInput");
                return;
            }else if(flag == 2){
                request.setAttribute("errorMsg","你没有权限操作该功能!");
                request.getRequestDispatcher("/WEB-INF/inc/error.jsp").forward(request,response);
            }
            String rel = (String) m.invoke(this,request,response);
            if(rel.startsWith(redirect)){
                response.sendRedirect(rel.substring(redirect.length()));
            }else{
                request.getRequestDispatcher("/WEB-INF/"+rel).forward(request,response);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    // 0 --> 表示成功访问
    // 1 --> 到登录页面
    // 2 --> 到错误页面
    private static int checkAuth(HttpServletRequest request,HttpServletResponse response,Method m){

        User lu = (User) request.getSession().getAttribute("loginUser");
        if(lu !=null && lu.getType() == 1) return 0;//管理员 可以访问所有
        //没有注解 ,该方法必须为admin访问
        if(!m.isAnnotationPresent(Auth.class)){
            if(lu == null) return 1;
            else if(lu.getType() != 1){
                request.setAttribute("errorMsg","你没有权限访问该功能!");
                return 2;
            }
        }else {
            Auth a = m.getAnnotation(Auth.class);
            String v = a.value();
            if(v.equals("any")){
                return 0;
            }else{ //else if(v.equals("user"))
                //登录用户才能访问
                if(lu == null )return 1;
            }
        }
        return 0;
    }
}
