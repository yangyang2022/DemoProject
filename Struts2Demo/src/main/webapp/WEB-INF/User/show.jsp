<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User show</title>
</head>
<body>
    <%--这里的JSTL 不一样 ,那里面是 ${xxx}}--%>
    <s:if test="#age<10">年龄小于10</s:if>
    <s:elseif test="#age >=10 and #age < 20">未成年</s:elseif>
    <s:else>已经成年了</s:else><br/>
    <%--使用了 iterator之后,会放入conpont 中,会有一个tempUser--%>
    <%--当使用var 时,会在actionContext里面存入一分 (key->u),--%>
<s:iterator value="#us" var="u" status="st">
    <s:property value="#u.id" />--<s:property value="username" /> --
    <s:property value="password"/>--<s:property value="#st.last" />
    -- <s:property value="#root[1].username" />
    <br/>
</s:iterator>
-- <s:property value="username" />
</body>
</html>
