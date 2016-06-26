<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="fileDemo" method="post" enctype="multipart/form-data">
    username: <input type="text" name="username"><br/>
    photo: <input type="file" name="photo"><br/>
    interets:<input type="checkbox" name="ints" value="足球">足球 &nbsp;
        <input type="checkbox" name="ints" value="篮球">篮球&nbsp;
        <input type="checkbox" name="ints" value="羽毛球">羽毛球<br/>
    <input type="submit" value="提交">
</form>
</body>
</html>
