<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>用户登录</title>
</head>
<body>
<jsp:include page="inc.jsp" />
<form action="user?method=login" method="post">
    <table width="900" align="center" class="thin-border">
        <tr>
            <td>用户姓名<input type="text" name="username"> </td>
        </tr>
        <tr>
            <td>用户密码<input type="password" name="password"></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="用户登录">&nbsp;
                <input type="reset" value="重置数据">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
