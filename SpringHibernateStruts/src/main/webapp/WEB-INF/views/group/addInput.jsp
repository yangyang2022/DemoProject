<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>添加组</h1>
    <s:form action="group_add" method="POST">
        <s:textfield label="组名称" name="groupName" />
        <s:submit value="添加" />
    </s:form>
</body>
</html>
