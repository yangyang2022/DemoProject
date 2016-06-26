package com.yangyang.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by syy on 2016/6/13.
 */
@WebFilter(filterName = "EndoingFilter",value = "/*")
public class EndoingFilter implements Filter {
    private static final String ENCODING = "UTF-8";
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        request.setCharacterEncoding(ENCODING);
        chain.doFilter(request, response);
        response.setCharacterEncoding(ENCODING);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
