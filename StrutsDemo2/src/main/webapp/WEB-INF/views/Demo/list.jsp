<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Demo list Page</h1>
    <s:iterator value="#us" var="e">
        ${e} -- <br/>
    </s:iterator>
</body>
</html>
