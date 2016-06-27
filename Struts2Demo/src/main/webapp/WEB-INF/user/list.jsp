<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title></title>
</head>
<body>
    <h1> User list </h1>
valueStack : ${username} -- ${password}--dep:<s:property value="#root[0].dep.name" /><br/>
    <s:property value="#root[0].username"/> -- <s:property value="password" /><br/>
${aaa} -- ${bbb} --
    <%--字符串不用加# ,其他 需要加 #--%>
    <s:property value="aaa" /> -- <s:property value="#bbb" /><br/>
${req} -- <s:property value="#request.req" /><br/>
<s:debug />
</body>
</html>
