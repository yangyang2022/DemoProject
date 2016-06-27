<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>Message addInput</title>
</head>
<s:debug />
<body>
    <form action="Message_add" method="post">
        ID: <input type="text" name="id" ><br/>
        Title:<input type="text" name="title" ><br/>
        Content:<input type="text" name="content" ><br/>
        <input type="submit" value="提交">
    </form>
</body>
</html>
