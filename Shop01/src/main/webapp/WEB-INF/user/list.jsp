<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>用户列表</title>
</head>
<body>
    <jsp:include page="inc.jsp" />
    <table width="900" align="center" cellpadding="0" cellspacing="0" class="thin-border">
        <tr>
            <td>用户标识</td>
            <td>用户名称</td>
            <td>用户密码</td>
            <td>用户昵称</td>
            <td>用户类型</td>
            <td>操作</td>
        </tr>
        <c:forEach var="u" items="${users.datas}">
            <tr>
                <td>${u.id}</td>
                <td>${u.username}</td>
                <td>${u.password}</td>
                <td><a href="user?method=show&id=${u.id}">${u.nickname}</a></td>
                <td>
                    <c:if test="${u.type == 0}">普通用户</c:if>
                    <c:if test="${u.type == 1}">管理员</c:if>
                    <a href="user?method=changeType&id=${u.id}">变更</a>
                </td>
                <td>
                    <a href="user?method=updateInput&id=${u.id}">修改</a>
                    <a href="user?method=delete&id=${u.id}">删除</a>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="7">所有记录: ${users.totalRecord}</td>
        </tr>
        <tr>
            <td colspan="7">
                <jsp:include page="../inc/pager.jsp">
                    <jsp:param name="items" value="${users.totalRecord}" />
                    <jsp:param name="url" value="user" />
                    <jsp:param name="params" value="method"/>
                </jsp:include>
            </td>
        </tr>
        </table>
</body>
</html>
