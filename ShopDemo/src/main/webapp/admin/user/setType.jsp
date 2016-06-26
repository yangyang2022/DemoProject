<%@ page import="DaoFactory" %>
<%@ page import="IUserDao" %>
<%@ page import="User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    IUserDao userDao = DaoFactory.getUserDao();
    User user = userDao.load(id);
    if(user.getType() == 0 ) {
        user.setType(1);
    }else user.setType(0);
    userDao.update(user);
    response.sendRedirect("list.jsp");
%>