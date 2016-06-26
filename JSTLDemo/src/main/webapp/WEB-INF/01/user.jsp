<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
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
</head>
<body>
    <c:if test="${empty aaa}">没有aaa</c:if> ---
    <c:if test="${not empty aaa}">没有aaa</c:if>

    <table width="900" align="center" border="1" cellpadding="0" cellspacing="0">
        <tr>
            <td>用户姓名</td>
            <td>用户昵称</td>
            <td>用户年龄</td>
        </tr>
        <c:forEach var="u" items="${users}" varStatus="s">
            <tr <c:if test="${s.index%2==0}">style="background: yellow;color: black" </c:if> >
                <td>${u.username}--${s.index}</td>
                <td>${u.nickname}</td>
                <td>
                <c:if test="${u.age <18}">未成年</c:if>
                <c:if test="${u.age >=18}">成年</c:if>
                </td>
            </tr>
        </c:forEach>
    </table>


</body>
</html>
