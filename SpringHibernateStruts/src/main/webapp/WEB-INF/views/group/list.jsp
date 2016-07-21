<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <s:iterator value="#gs">
        ${id} -- <a href="group_show?cid=${id}">${groupName}</a>
        -- <a href="group_delete?cid=${id}">删除</a>
        <br/>
    </s:iterator>
</body>
</html>
