<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>用户更新</title>
</head>
<body>
<jsp:include page="inc.jsp" />
<form action="user?method=update" method="post">
    <input type="hidden" name="id" value="${user.id}">
    <table width="900" align="center" class="thin-border">
        <tr>
            <td>用户姓名: ${user.username} </td>
        </tr>
        <tr>
            <td>用户密码<input type="password" name="password" value="${user.password}"></td>
        </tr>
        <tr>
            <td>用户昵称<input type="text" name="nickname" value="${user.nickname}"></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="用户更新">&nbsp;
                <input type="reset" value="重置数据">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
