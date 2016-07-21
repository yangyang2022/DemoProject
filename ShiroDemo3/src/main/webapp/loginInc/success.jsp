<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>login Success [ <shiro:principal /> ] </h1>
    <a href="<%=request.getContextPath()%>/logout">退出</a>
</body>
</html>
