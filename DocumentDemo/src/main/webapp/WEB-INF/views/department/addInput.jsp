<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加部门</title>
</head>
<body>
    <s:form action="department_add" method="POST">
        <s:textfield label="部门名称" name="name" />
        <s:submit value="添加部门" />
    </s:form>
</body>
</html>
