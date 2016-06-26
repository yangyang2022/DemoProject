package com.yangyang.filter;

import com.yangyang.mode.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by syy on 2016/6/16.
 */
@WebFilter(filterName = "AdminCheckFilter",value = "/admin/*")
public class AdminCheckFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        User user = (User)request.getSession().getAttribute("loginUser");
        if(user == null){
            //System.out.println("user: "+user);
            response.sendRedirect(request.getContextPath()+"/loginInput.jsp");
            return;
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
