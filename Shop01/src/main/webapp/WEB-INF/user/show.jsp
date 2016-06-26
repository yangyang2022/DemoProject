<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>用户详细信息,当前用户[ ${user.username} ]</title>
</head>
<body>
<jsp:include page="inc.jsp" />
<table width="1000" align="center" class="thin-border">
    <tr>
        <td>用户姓名</td><td>${user.username}</td>
    </tr>
    <tr>
        <td>用户昵称</td><td>${user.nickname}</td>
    </tr>
    <tr>
        <td>用户类型</td><td>
        <c:if test="${user.type == 0}">普通用户</c:if>
        <c:if test="${user.type == 1}">管理员</c:if>
    </td>
    </tr>
    <tr>
        <td colspan="2">
            <a href="http://">用户订单查询</a>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            用户联系地址
            <a href="address?method=addInput&user_id=${user.id}">
            <c:if test="${user.id == loginUser.id}">添加地址</c:if>
            </a>
        </td>
    </tr>
    <c:if test="${empty user.addresses}">
        <tr>
            <td colspan="2">
                没有添加地址
            </td>
        </tr>
    </c:if>
    <c:if test="${not empty user.addresses}">
        <c:forEach items="${user.addresses}" var="address">
            <tr>
                <td colspan="2">[ 地址 ]: ${address.name} [ 电话 ]: ${address.phone} [ 邮编 ]:${address.postcode}
                    &nbsp;
                    <a href="address?method=updateInput&id=${address.id}">修改</a> &nbsp;
                    <a href="address?method=delete&id=${address.id}&user_id=${user.id}">删除</a>
                </td>
            </tr>
        </c:forEach>
    </c:if>
</table>
</body>
</html>
