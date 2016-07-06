<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1> hello yangyang </h1>
    time: ${time} -- ${name} -- request: ${requestScope.user} session:-- ${sessionScope.user}<br/>

    -- i18n: <fmt:message key="i18n.username" /> ---
        <fmt:message key="i18n.password" />

</body>
</html>
