<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>用户更新</h1>
    <s:form action="user_update" method="POST">
        <s:hidden name="id" />
        <s:textfield label="用户名称" name="username" />
        <s:textfield label="用户密码" name="password" />
        <s:textfield label="用户昵称" name="nickname" />
        <s:select list="#gs" label="用户所在组"
                  name="gid"
                  listKey="id" listValue="groupName">

        </s:select>
        <s:submit value="更新" />
    </s:form>
</body>
</html>
