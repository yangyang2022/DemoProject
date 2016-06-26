<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>所有用户</title>
</head>
<style type="text/css">
    table{
        border-bottom: 1px solid #000;
        border-right: 1px solid #000;
    }
    table td{
        border-top: 1px solid #000;
        border-left: 1px solid #000;
        padding: 5px;
    }
</style>
<a href="user?method=register">注册新用户</a><br/>
    <table align="center" width="900" cellspacing="0" cellpadding="0">
        <tr>
            <td>用户姓名</td>
            <td>用户昵称</td>
            <td>用户年龄</td>
        </tr>
        <c:if test="${empty users}">
            <td colspan="3">没有用户信息</td>
        </c:if>
        <c:forEach var="u" items="${users}">
            <tr>
                <td>${u.username}</td>
                <td>${u.nickname}</td>
                <td>${u.age}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
