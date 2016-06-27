<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>Message update</title>
</head>
<s:debug />
<body>
    <form action="Message_add" method="post">
        ID: <input type="text" name="id" value="<s:property value="id"/>"><br/>
        Title:<input type="text" name="title" value="<s:property value="title"/>"><br/>
        Content:<input type="text" name="content" value="<s:property value="content"/>"><br/>
        PointDemo:<input type="text" name="point" value="<s:property value="point" /> "><br/>
        createDate:<input type="text" name="createDate" value="<s:property value="createDate"/> "><br/>
        endDate:<input type="text" name="endDate" value="<s:property value="endDate"/> "><br/>

        <input type="submit" value="提交">
    </form>
</body>
</html>
