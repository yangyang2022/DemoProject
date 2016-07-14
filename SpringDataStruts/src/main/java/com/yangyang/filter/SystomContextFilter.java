package com.yangyang.filter;

import com.yangyang.systemContext.SystemContext;

import javax.servlet.*;
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
            } catch (NumberFormatException e) {
            }
            SystemContext.setPageSize(pageSize);
            SystemContext.setPageOffset(pageOffset);

            chain.doFilter(request, resp);
        } finally {
            SystemContext.removePageOffset();
            //SystemContext.removePageIndex();
            SystemContext.removePageSize();
        }


    }

    public void init(FilterConfig config) throws ServletException {

    }

}
