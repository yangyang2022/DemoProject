<%--
  Created by IntelliJ IDEA.
  User: syy
  Date: 2016/6/13
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="../jsp/demo.jsp">访问demo.jsp文件</a>
    <a href="../img/1.jpg">图片(相对路径)</a>
    <a href="<%=request.getContextPath()%>/img/3.jpg">图片2(绝对路径)</a>
</body>
</html>
