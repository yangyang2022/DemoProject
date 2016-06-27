<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    ${message.id} -- ${message.title} -- ${message.content}
    -- ${point} -- <s:property value="point" />
    -- <s:property value="message.createDate" />
    -- <s:property value="endDate" />
</body>
</html>
