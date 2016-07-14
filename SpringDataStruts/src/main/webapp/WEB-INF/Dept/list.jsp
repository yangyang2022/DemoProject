<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Employee List</h1>
    <s:if test="#deps == null || #deps.size() == 0 ">
        没有员工信息
    </s:if>
    <s:else>
        <table border="1" cellspacing="0" cellpadding="10">
            <tr>
                <td>ID</td>
                <td>DepartmentName</td>
            </tr>
            <s:iterator value="#deps.getContent()" var="e">
                <tr>
                    <td>${e.id}</td>
                    <td>
                        ${e.departmentName} &nbsp;
                            <a href="Dept_update?id=${e.id}">修改</a>&nbsp;
                            <a href="Dept_delete?id=${e.id}">删除</a>
                    </td>
                </tr>
            </s:iterator>
            <tr>
                <td colspan="2">
                    <jsp:include page="../inc/pager.jsp">
                        <jsp:param name="items" value="${deps.getTotalElements()}" />
                    </jsp:include>
                </td>
            </tr>
        </table>
    </s:else>
</body>
</html>
