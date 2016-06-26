<%@ page import="IUserDao" %>
<%@ page import="DaoFactory" %>
<%@ page import="MsgException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    IUserDao userDao = DaoFactory.getUserDao();
    int id = Integer.parseInt(request.getParameter("id"));
    try {
        userDao.delete(id);
        response.sendRedirect("list.jsp");
        return;
    } catch (ShopException e) {
        %>
    <h2>删除出现错误 <%=e.getMessage()%></h2>
<%
    }
%>