<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: syy
  Date: 2016/6/28
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Group AddInput</title>
</head>
<body>
    <%--<form action="Group_add" method="post">--%>
        <%--组标识:<input type="text" name="id" value="${group.id}"/><br/>--%>
        <%--组名称:<input type="text" name="name" value="${group.name}"/><br/>--%>
        <%--<input type="submit" value="提交">--%>
    <%--</form>--%>
    <!-- 在Struts中 使用%代替# 取值 -->
    <s:form action="Group_add" method="POST">
        <s:textfield label="组标识" name="id"/>
        <s:textfield label="组名称" name="name" />
        <s:textfield label="用户名" name="username" />
        <s:checkboxlist name="ints" label="兴趣"
                        list="#{'foot':'足球','basket':'篮球','pingpang':'乒乓球'}"
                        value="#ints"
                        listKey="key" listValue="value" />
        <s:radio list="#{'0':'男','1':'女'}" label="性别" name="gender" value="1"/>

        <s:select list="#gs" name="groups" label="部门选择"
                  listKey="id" listValue="name"
                  headerKey="-1" headerValue="请选择部门"
                  value="2"
        />
        <s:submit value="提交" />
    </s:form>
</body>
</html>
