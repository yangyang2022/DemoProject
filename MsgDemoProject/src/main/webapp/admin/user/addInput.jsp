<%@ page import="java.util.Map" %>
<%@ page import="ValidateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User add</title>
</head>

<%
    Map<String,String> errorMap = (Map<String, String>) request.getAttribute("error");
    String usernameError = ValidateUtil.showErrorMsg(errorMap,"username");
    String passwordError = ValidateUtil.showErrorMsg(errorMap,"password");
    String nicknameError = ValidateUtil.showErrorMsg(errorMap,"nickname");
%>
<body>
    <jsp:include page="inc.jsp">
        <jsp:param name="op" value="添加" />
    </jsp:include>
    <form action="add.jsp" method="post">
        <table align="center" width="500" border="1">
            <tr>
                <td>用户姓名: </td><td><input type="text" name="username" value="<%=request.getParameter("username")%>"/><%=usernameError%></td>
            </tr>
            <tr>
                <td>用户密码: </td><td><input type="password" name="password" value="<%=request.getParameter("password")%>"/><%=passwordError%></td>
            </tr>
            <tr>
                <td>用户昵称: </td><td><input type="text" name="nickname" value="<%=request.getParameter("nickname")%>"/><%=nicknameError%></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="添加用户"></td>
            </tr>
        </table>
    </form>
</body>
</html>
