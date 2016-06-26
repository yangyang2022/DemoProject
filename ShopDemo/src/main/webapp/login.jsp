<%@ page import="IUserDao" %>
<%@ page import="DaoFactory" %>
<%@ page import="User" %>
<%@ page import="MsgException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    IUserDao userDao = DaoFactory.getUserDao();
    try {
        User user = userDao.login(username,password);
        session.setAttribute("loginUser",user);
        response.sendRedirect(request.getContextPath()+"/admin/user/list.jsp");
    } catch (ShopException e) {
        %>
    <h2>发生错误: <%=e.getMessage()%></h2>
<%
    }catch (Exception ee){
        %>
    <h2>发生未知错误: <%=ee.getMessage()%></h2>
<%
    }

%>