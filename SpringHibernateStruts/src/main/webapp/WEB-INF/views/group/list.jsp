<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <s:iterator value="#gs">
        ${id} -- <a href="group_show?id=${id}">${groupName}</a>
        -- <a href="group_delete?id=${id}">删除</a>&nbsp;
        -- <a href="group_updateInput?id=${id}">更新</a>&nbsp;
        <br/>
    </s:iterator>
</body>
</html>
