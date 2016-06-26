<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <%
        request.setAttribute("username","<h2>yangyang</h2>");
    %>
</head>
<body>
    ${username} --> ${hello} <--<br/>
    ${param.hello}
    <c:out value="${username2}" escapeXml="false" default="查无此人"></c:out>
hello world
</body>
</html>
