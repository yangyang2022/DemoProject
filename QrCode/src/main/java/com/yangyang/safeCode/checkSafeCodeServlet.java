package com.yangyang.safeCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "checkSafeCodeServlet",value = "/checkSafeCode")
public class checkSafeCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String safeCode = (String) request.getSession().getAttribute("safeCode");
        String checkCode = request.getParameter("checkcode");
        System.out.println(safeCode+" -> "+checkCode);

        PrintWriter out = response.getWriter();
        if(safeCode.equals(checkCode.toUpperCase())){
            out.write("safecode success!");
        }else {
            out.write("safecode fail!");
        }
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
