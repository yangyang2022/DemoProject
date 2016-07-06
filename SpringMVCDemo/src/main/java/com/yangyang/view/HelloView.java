package com.yangyang.view;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * 自定义 view
 *  -- 1: 首先在springmvc.xml 里面配置 BeanNameViewResolver
 */
@Component("helloView")
public class HelloView implements View {
    @Override
    public String getContentType() {
        return "text/html;charset=UTF-8";
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.getWriter().println("hello view ... "+new Date());
    }
}
