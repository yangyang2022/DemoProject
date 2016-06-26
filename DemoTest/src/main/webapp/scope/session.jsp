<%@ page import="User" %><%--
  Created by IntelliJ IDEA.
  User: syy
  Date: 2016/6/13
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="demo2.jsp">session</a>
    <jsp:forward page="demo2.jsp" />

<%
    User user = new User(1,"yangyang","chairman");
    session.setAttribute("user",user);
%>
</body>
</html>
