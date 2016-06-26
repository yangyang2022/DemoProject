<%@ page import="IUserDao" %>
<%@ page import="DaoFactory" %>
<%@ page import="com.yangyang.mode.MsgException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="/inc/adminCheck.jsp"%>
<%
    IUserDao userDao = DaoFactory.getUserDao();
    int id = Integer.parseInt(request.getParameter("id"));
    try {
        userDao.delete(id);
        response.sendRedirect("list.jsp");
        return;
    } catch (MsgException e) {
        %>
    <h2>删除出现错误 <%=e.getMessage()%></h2>
<%
    }
%>