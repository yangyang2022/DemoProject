<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加用户</title>
</head>
<body>
    <s:form action="user_add" method="POST">
        <s:textfield label="用户名称" name="username" />
        <s:textfield label="用户昵称" name="nickname" />
        <s:password label="用户密码" name="password" />
        <s:textfield label="用户邮箱" name="email" />
        <s:select list="#ds" label="用户所在部门" listKey="id" listValue="name" name="depId" />
        <s:radio list="#{'0':'普通用户','1':'管理员'}" listKey="key" listValue="value"
                 name="type" />
        <s:submit value="添加用户" />
    </s:form>
</body>
</html>
