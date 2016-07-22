<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>更新组</h1>
    <s:form action="group_update" method="POST">
        <s:hidden name="id" />
        <s:textfield label="组名称" name="groupName" />
        <s:submit value="更新" />
    </s:form>
</body>
</html>
