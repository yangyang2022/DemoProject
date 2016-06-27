<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>Message addInput</title>
</head>
<s:debug />
<body>
    <form action="Message_file" method="post" enctype="multipart/form-data">
        Title:<input type="text" name="title" ><br/>
        File:<input type="file" name="photo"><br/>
        <input type="submit" value="提交">
    </form>
</body>
</html>
