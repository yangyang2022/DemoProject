<%@ page import="User" %>
<%@ page import="IUserDao" %>
<%@ page import="DaoFactory" %>
<%@ page import="MsgException" %>
<%@ page import="ValidateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String nickname = request.getParameter("nickname");

    boolean validate = ValidateUtil.validateNull(request,new String[]{"username","password","nickname"});

    if(!validate){
        %>
        <jsp:forward page="addInput.jsp" />
    <%
    }

    User user = new User(username,password,nickname,0,0);
        // status and type -> 0 is open 0 is ord_user
    IUserDao userDao = DaoFactory.getUserDao();
    try {
        userDao.add(user);
        //add success
        response.sendRedirect("list.jsp");
        return;
    } catch (ShopException e) {
        %>
    <h2 style="color: red"> 发生错误: <%=e.getMessage()%></h2>
<%
    }catch (Exception ee){
        %>
    <h2 style="color: red"> 发生未知错误: <%=ee.getMessage()%></h2>
    <%
    }
%>