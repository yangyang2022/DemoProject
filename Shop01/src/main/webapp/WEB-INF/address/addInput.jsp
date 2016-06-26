<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>地址添加</title>
</head>
<body>
<form action="address?method=add" method="post">
    <input type="hidden" name="user_id" value="${param.user_id}">
    <table width="900" align="center" class="thin-border">
        <tr>
            <td>添加地址的用户</td><td>${user.nickname}</td>
        </tr>
        <tr>
            <td>详细地址</td>
            <td>
                <input type="text" name="name" value="${param.name}" size="60"><span class="errorContent">${errors.name}</span>
            </td>
        </tr>
        <tr>
            <td>联系电话</td>
            <td>
                <input type="text" name="phone" value="${param.phone}"><span class="errorContent">${errors.phone}</span>
            </td>
        </tr>
        <tr>
            <td>邮政编码</td>
            <td>
                <input type="text" name="postcode" value="${param.postcode}"><span class="errorContent">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="注册地址">&nbsp;
                <input type="reset" value="重置数据">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
