package com.yangyang.mvc;

import com.yangyang.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@WebServlet(name = "user",value = "/user")
public class UserServlet extends BaseServlet {

    public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = (List<User>) request.getSession().getServletContext().getAttribute("storeUsers");
        request.setAttribute("users",users);
        return "/user/list.jsp";
    }
    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String nickname = request.getParameter("nickname");
        int age = Integer.parseInt(request.getParameter("age"));
        List<User> users = (List<User>) request.getSession().getServletContext().getAttribute("storeUsers");
        if(users == null){
            users = new ArrayList<>();
        }
        User u = new User(username,nickname,age);
        users.add(u);
        request.getSession().getServletContext().setAttribute("storeUsers",users);
        return "redirect:user?method=list";
    }
    public String register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "user/register.jsp";
    }
    //protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //    this.doPost(request, response);
    //}
}
