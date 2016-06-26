<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title></title>
</head>
<body>
    <h1> User list </h1>
${username} -- ${password}<br/>
<s:property value="username"/> -- <s:property value="password" /><br/>
${aaa} -- ${bbb} --
    <%--字符串不用加# ,其他 需要加 #--%>
    <s:property value="aaa" /> -- <s:property value="#bbb" /><br/>
${req} -- <s:property value="#request.req" />
</body>
</html>
