package com.yangyang.filter;

import com.yangyang.model.User;
import com.yangyang.systemContext.SystemContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SystomContextFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        String con = request.getParameter("con");
        if(con == null) con = "";

        int pageSize = 15;
        int pageOffset = 0;

        try {
            try {
                pageOffset = Integer.parseInt(request.getParameter("pager.offset"));
            } catch (NumberFormatException e) {}

            try {
                pageSize = Integer.parseInt(request.getParameter("pager.size"));
            } catch (NumberFormatException e) {}

            SystemContext.setPageSize(pageSize);
            SystemContext.setPageOffset(pageOffset);

            User loginUser = (User) ((HttpServletRequest) request).getSession().getAttribute("loginUser");
            if(loginUser != null) SystemContext.setLoginUser(loginUser);

            String realPath = "C:\\mavenProject\\DemoProject\\DocumentDemo\\src\\main\\webapp";
            SystemContext.setRealPath(realPath);

            chain.doFilter(request, resp);
        } finally {
            SystemContext.removePageOffset();
            SystemContext.removePageSize();
            SystemContext.removeLoginUser();
            SystemContext.removeRealPath();
        }


    }

    public void init(FilterConfig config) throws ServletException {

    }

}
