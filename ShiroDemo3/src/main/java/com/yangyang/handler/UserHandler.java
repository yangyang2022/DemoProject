package com.yangyang.handler;

import com.yangyang.model.shiroModel.User;
import com.yangyang.utils.ShiroUtile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class UserHandler {

    @RequestMapping("/shiro-login")
    public String login(@RequestParam("username")String username, @RequestParam("password")String password ,
                        HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = new User(username,password);

        String errorMsg = ShiroUtile.login(user);
        if(errorMsg == null){
            //login success
            return "redirect:/loginInc/success.jsp";
        }
        request.setAttribute("error",errorMsg);
        request.getRequestDispatcher("index.jsp").forward(request,response);
        return "inc/error";
    }
}
