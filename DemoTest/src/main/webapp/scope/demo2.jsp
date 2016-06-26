<%@ page import="User" %><%--
  Created by IntelliJ IDEA.
  User: syy
  Date: 2016/6/13
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Demo2</title>
</head>
<body>
<%--
        四个内置对象:
        set: xxx.setAttribute(xxx,object)
        get: xxx.getAttribute(xxx)

        pageContext(no in servlet) 当前页面存在 ,页面跳转 信息丢失
        request(HttpServlet) 服务器跳 存在(跟跳转位置有关),客户端 不存在
        session(HttpSession) 携带信息
        application(ServletContext)
    --%>

<%
    User user = (User)request.getAttribute("user");
%>
<%=user%>

</body>
</html>
