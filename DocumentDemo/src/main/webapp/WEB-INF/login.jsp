<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <s:form action="login!login" method="POST">
        <s:textfield name="username" label="用户姓名" />
        <s:password name="password" label="用户密码" />
        <s:submit value="登录" />
    </s:form>
</body>
</html>
