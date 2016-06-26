<%@ page import="User" %><%--
  Created by IntelliJ IDEA.
  User: syy
  Date: 2016/6/13
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>redirect01</title>
</head>
<body>
<%-- response redirect ,记录跳转~ 后面代码任然执行 ... --%>
    <%
        response.sendRedirect("demo2.jsp?username=yangyang");
        return;
//        return;
    %>
</body>
</html>
