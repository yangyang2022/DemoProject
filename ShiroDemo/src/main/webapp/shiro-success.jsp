<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Success page !!!</h1>

    Welcome: <shiro:principal />

    <shiro:hasAnyRoles name="user">
        <a href="user.jsp">User.jsp</a><br/><br/>
    </shiro:hasAnyRoles>
    <shiro:hasAnyRoles name="admin">
        <a href="admin.jsp">admin.jsp</a><br/><br/>
    </shiro:hasAnyRoles>

    <a href="shiro-logout">Logout</a>
</body>
</html>
