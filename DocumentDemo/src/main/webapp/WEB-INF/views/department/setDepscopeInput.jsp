<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>设置可发文部门</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
</head>
<body>
<jsp:include page="nav.jsp" />
    <form action="department_setDepscope" method="post">
        <table width="900"class="ct" align="center" cellpadding="0" cellspacing="0">
            <tr>
                <td>部门标识</td><td>${department.id}</td>
            </tr>
            <tr>
                <td>部门名称</td><td>${department.name}</td>
            </tr>
            <tr>
                <td colspan="2">设置可发文部门</td>
            </tr>
            <tr>
                <td colspan="2">
                    <s:checkboxlist theme="simple" list="#ds" listKey="id" listValue="name"
                                    name="sdeps" value="#ads"/>
                    <s:hidden name="id" value="%{department.id}" />
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="添加可设置发文部门" />
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
