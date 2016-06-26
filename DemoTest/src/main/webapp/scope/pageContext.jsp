<%@ page import="User" %><%--
  Created by IntelliJ IDEA.
  User: syy
  Date: 2016/6/13
  Time: 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PageContextDemo</title>
</head>
<body>
    <a href="demo2.jsp">pageContext</a>
    <jsp:forward page="demo2.jsp" />
<%
    User user = new User(1,"yangyang","chairman");
    pageContext.setAttribute("user",user);
%>
<%
    User uu = (User) pageContext.getAttribute("user");
%>
<%=uu%>

</body>
</html>
