<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

${error}
<form action="shiro-login" method="post">
    username:<input type="text" name="username" /><br/>
    password:<input type="password" name="password" /><br/>
    <input type="submit" value="登录" />
</form>
</body>

</html>
