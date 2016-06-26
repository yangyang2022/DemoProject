package com.yangyang.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by syy on 2016/6/16.
 */
@WebFilter(filterName = "EncodingFilter",value = "/*")
public class EncodingFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        chain.doFilter(req, resp);
        resp.setCharacterEncoding("UTF-8");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
