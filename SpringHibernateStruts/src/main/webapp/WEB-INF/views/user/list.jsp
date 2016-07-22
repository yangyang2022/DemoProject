<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>User List </h1>
    <s:iterator value="#us.data">
        ${id} -- <a href="user_show?id=${id}">${username} </a>
        -- ${password}
        -- ${nickname}
        -- ${group.groupName}
        -- <a href="user_delete?id=${id}">删除</a>
        -- <a href="user_updateInput?id=${id}">更新</a>
        <br/>
    </s:iterator>
    <jsp:include page="../inc/pager.jsp">
        <jsp:param name="url" value="user_list" />
        <jsp:param name="items" value="${us.totalRecord}" />
    </jsp:include>
</body>
</html>
