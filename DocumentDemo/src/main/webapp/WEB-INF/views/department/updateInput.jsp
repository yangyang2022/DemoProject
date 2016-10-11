<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更新部门</title>
</head>
<body>
    <s:form action="department_update" method="POST">
        <s:hidden name="id" />
        <s:textfield label="部门名称" name="name" />
        <s:submit value="更新部门" />
    </s:form>
</body>
</html>
