<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>用户注册</title>
</head>
<body>
<jsp:include page="inc.jsp" />
<form action="user?method=add" method="post">
    <table width="900" align="center" class="thin-border">
        <tr>
            <td>用户姓名<input type="text" name="username" value="${param.username}"><span class="errorContent">${errors.username}</span> </td>
        </tr>
        <tr>
            <td>用户密码<input type="password" name="password" value="${param.password}"><span class="errorContent">${errors.password}</span> </td>
        </tr>
        <tr>
            <td>用户昵称<input type="text" name="nickname" value="${param.nickname}"><span class="errorContent">${errors.nickname} </span></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="用户注册">&nbsp;
                <input type="reset" value="重置数据">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
