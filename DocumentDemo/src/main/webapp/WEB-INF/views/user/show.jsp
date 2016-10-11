<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户信息</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
</head>
<body>
<jsp:include page="nav.jsp" />
    <table width="900"class="ct" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td>用户标识</td><td>${id}</td>
        </tr>
        <tr>
            <td>用户名称</td><td>${username}</td>
        </tr>
        <tr>
            <td>用户昵称</td><td>${nickname}</td>
        </tr>
        <tr>
            <td>用户邮箱</td><td>${email}</td>
        </tr>
        <tr>
            <td>用户类型</td><td><s:if test="type==0">普通用户</s:if><s:else>管理员</s:else></td>
        </tr>
        <tr>
            <td>用户所在部门</td><td>${department.name}</td>
        </tr>
    </table>
</body>
</html>
