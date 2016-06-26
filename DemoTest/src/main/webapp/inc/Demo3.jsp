<%--
  Created by IntelliJ IDEA.
  User: syy
  Date: 2016/6/13
  Time: 12:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%-- include 静态包含 --%>
<jsp:include page="Top.jsp" />
<%
    String str = "hello yangyang";
%>
<h1>xxx系统</h1>
<%=str%>

</body>
</html>
