<%@ page import="IUserDao" %>
<%@ page import="User" %>
<%@ page import="DaoFactory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    int id = Integer.parseInt(request.getParameter("id"));
    IUserDao userDao = DaoFactory.getUserDao();
    User user = userDao.load(id);
    if(user.getStatus() == 0 ) {
        user.setStatus(1);
    }else user.setStatus(0);
    userDao.update(user);
    response.sendRedirect("list.jsp");
%>