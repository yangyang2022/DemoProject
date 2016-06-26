<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<jsp:include page="inc/top.jsp" />
    <form action="login.jsp" method="post">
        用户姓名:<input type="text" name="username"></br>
        用户密码:<input type="password" name="password"></br>
        <input type="submit" value="登录">
    </form>
</body>
</html>
