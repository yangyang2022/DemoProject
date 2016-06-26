package com.yangyang.mvc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        String WEB_INF = "/WEB-INF/";
        try {
            Method m = this.getClass().getMethod(method,HttpServletRequest.class,HttpServletResponse.class);
            String rel = (String) m.invoke(this,request,response);
            System.out.println(WEB_INF+rel);
            String redirect = "redirect:";
            if(rel.startsWith(redirect)){
                response.sendRedirect(rel.substring(redirect.length()));
            }else {
                request.getRequestDispatcher(WEB_INF+rel).forward(request,response);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
