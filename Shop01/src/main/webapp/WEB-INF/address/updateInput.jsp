<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>地址更新</title>
</head>
<body>
<form action="address?method=update" method="post">
    <input type="hidden" name="id" value="${address.id}">
    <table width="900" align="center" class="thin-border">
        <tr>
            <td>修改地址的用户</td><td>${address.user.nickname}</td>
        </tr>
        <tr>
            <td>详细地址</td>
            <td>
                <input type="text" name="name" value="${address.name}" size="60"><span class="errorContent">${errors.name}</span>
            </td>
        </tr>
        <tr>
            <td>联系电话</td>
            <td>
                <input type="text" name="phone" value="${address.phone}"><span class="errorContent">${errors.phone}</span>
            </td>
        </tr>
        <tr>
            <td>邮政编码</td>
            <td>
                <input type="text" name="postcode" value="${address.postcode}"><span class="errorContent">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="地址更新">&nbsp;
                <input type="reset" value="重置数据">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
