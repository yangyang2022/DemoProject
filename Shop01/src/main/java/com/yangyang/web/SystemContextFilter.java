package com.yangyang.web;

import com.yangyang.model.SystemContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "SystemContextFilter",urlPatterns = "/*")
public class SystemContextFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        try {
            int pageOffset = 0;
            int pageSize = 15;
            String sort = req.getParameter("sort");
            String order = req.getParameter("order");
            try {
                pageOffset = Integer.parseInt(req.getParameter("pager.offset"));
            } catch (NumberFormatException e) {

            }
            SystemContext.setSort(sort);
            SystemContext.setOrder(order);
            SystemContext.setPageOffset(pageOffset);
            SystemContext.setPageSize(pageSize);
            chain.doFilter(req, resp);
        } finally {
            SystemContext.removeOrder();
            SystemContext.removeSort();
            SystemContext.removePageOffset();
            SystemContext.removePageSize();
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
