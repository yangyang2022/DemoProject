<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>展示部门</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
</head>
<body>
<jsp:include page="nav.jsp" />
    <table width="900"class="ct" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td>部门标识</td><td>${department.id}</td>
        </tr>
        <tr>
            <td>部门名称</td><td>${department.name}</td>
        </tr>
        <tr>
            <td colspan="2">可发文部门</td>
        </tr>
        <tr>
            <td colspan="2">
                <s:iterator value="#ds">
                     ${name}
                </s:iterator>
            </td>
        </tr>
    </table>
</body>
</html>
