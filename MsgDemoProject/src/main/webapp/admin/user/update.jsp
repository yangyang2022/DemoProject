<%@ page import="User" %>
<%@ page import="IUserDao" %>
<%@ page import="DaoFactory" %>
<%@ page import="com.yangyang.mode.MsgException" %>
<%@ page import="ValidateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    int id = Integer.parseInt(request.getParameter("id"));

    String password = request.getParameter("password");
    String nickname = request.getParameter("nickname");

    boolean validate = ValidateUtil.validateNull(request,new String[]{"password","nickname"});

    if(!validate){
        %>
        <jsp:forward page="updateInput.jsp" />
    <%
    }

    IUserDao userDao = DaoFactory.getUserDao();
    User user = userDao.load(id);
        user.setPassword(password);
        user.setNickname(nickname);
    try {
        userDao.update(user);
        //add success
        response.sendRedirect("list.jsp");
        return;
    } catch (MsgException e) {
        %>
    <h2 style="color: red"> 发生错误: <%=e.getMessage()%></h2>
<%
    }catch (Exception ee){
        %>
    <h2 style="color: red"> 发生未知错误: <%=ee.getMessage()%></h2>
    <%
    }
%>