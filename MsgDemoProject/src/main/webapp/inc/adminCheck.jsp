<%@ page import="User" %>
<%@ page import="com.yangyang.mode.MsgException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User loginUser = (User) session.getAttribute("loginUser");
    if(loginUser.getType() != 1){
        throw new MsgException("没有权限操作该功能!");
    }
%>