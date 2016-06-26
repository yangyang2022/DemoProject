<%@ page import="java.util.List" %>
<%@ page import="User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.yangyang.model.UserInit" %><%--
  Created by IntelliJ IDEA.
  User: syy
  Date: 2016/6/13
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Object</title>

    <%
        List<User> users = UserInit.initUsers();

    %>
</head>
<body>
<%=request.getParameter("username")%>
    <table width="400" align="center" border="1">
        <tr>
            <td>id</td><td>username</td><td>nickname</td>
        </tr>
        <%
            for(User u:users){
                %>
            <tr>
                <td><%=u.getId()%></td><td><%=u.getUsername()%></td><td><%=u.getNickname()%></td>
            </tr>
        <%
            }
        %>
    </table>
</body>
</html>
