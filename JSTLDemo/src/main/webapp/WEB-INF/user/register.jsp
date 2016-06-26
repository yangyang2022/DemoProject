<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
</head>
<body>

<form action="user?method=add" method="post">
    用户姓名:<input type="text" name="username" ><br/>
    用户昵称:<input type="text" name="nickname"><br/>
    用户年龄:<input type="text" name="age"><br/>
    <input type="submit" value="用户注册">
</form>
</body>
</html>
