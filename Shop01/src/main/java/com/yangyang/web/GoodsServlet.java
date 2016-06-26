package com.yangyang.web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "GoodsServlet",urlPatterns = "/goods")
public class GoodsServlet extends BaseServlet {

    @Auth("any")
    public String list(HttpServletRequest request, HttpServletResponse response){
        return "goods/list.jsp";
    }
}
