<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User show</title>
</head>
<body>
    <s:if test="#age<10">年龄小于10</s:if>
    <s:elseif test="#age >=10 && #age < 20">未成年</s:elseif>
    <s:else>已经成年了</s:else>
</body>
</html>
