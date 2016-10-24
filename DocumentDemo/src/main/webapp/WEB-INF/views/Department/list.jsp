<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>部门列表</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
</head>
<body>
    <jsp:include page="nav.jsp" />
    <table class="ct" width="900" align="center" border="1" cellspacing="0" cellpadding="0">
        <tr>
            <td>部门ID</td>
            <td>部门名称</td>
            <td>操作</td>
        </tr>
        <s:iterator value="#ds">
            <tr>
                <td>${id}</td>
                <td><a href="department_show?id=${id}">${name}</a></td>
                <td>
                    <a href="department_updateInput?id=${id}">更新</a>
                    <a href="department_delete?id=${id}">删除</a>
                    <a href="department_setDepscopeInput?id=${id}">设置可发文部门</a>
                </td>
            </tr>

        </s:iterator>
    </table>
</body>
</html>
