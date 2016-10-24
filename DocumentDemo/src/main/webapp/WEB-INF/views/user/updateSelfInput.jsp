<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改个人信息</title>
</head>
<body>
    <s:form action="user_updateSelf" method="POST">
        <s:textfield label="用户名称" name="username" disabled="true"/>
        <s:textfield label="用户昵称" name="nickname" />
        <s:textfield label="用户密码" name="password" disabled="true" />
        <s:textfield label="用户邮箱" name="email" />
        <s:select list="#ds" label="用户所在部门" listKey="id" listValue="name"
                  name="depId" value="%{department.id}" disabled="true"/>
        <s:radio list="#{'0':'普通用户','1':'管理员'}" listKey="key" listValue="value"
                 name="type"  disabled="true"/>
        <s:submit value="修改个人信息" />
    </s:form>
</body>
</html>
