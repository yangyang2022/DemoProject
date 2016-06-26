<%@ page import="java.util.Map" %>
<%@ page import="ValidateUtil" %>
<%@ page import="User" %>
<%@ page import="DaoFactory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Update</title>
</head>
<%
    User user = (User) session.getAttribute("loginUser");
    Map<String,String> errorMap = (Map<String, String>) request.getAttribute("error");
//    String usernameError = ValidateUtil.showErrorMsg(errorMap,"username");
    String passwordError = ValidateUtil.showErrorMsg(errorMap,"password");
    String nicknameError = ValidateUtil.showErrorMsg(errorMap,"nickname");
%>
<body>
    <jsp:include page="inc.jsp">
        <jsp:param name="op" value="更新" />
    </jsp:include>
    <form action="updateSelf.jsp" method="post">
        <table align="center" width="500" border="1">
            <input type="hidden" name="id" value="<%=user.getId()%>">
            <tr>
                <td>用户姓名: </td><td><%=user.getUsername()%></td>
            </tr>
            <tr>
                <td>用户密码: </td><td><input type="password" name="password" value="<%=user.getPassword()%>"/><%=passwordError%></td>
            </tr>
            <tr>
                <td>用户昵称: </td><td><input type="text" name="nickname" value="<%=user.getNickname()%>"/><%=nicknameError%></td>
            </tr>
            <tr>
                <td><input type="submit" value="更新用户"></td>
                <td><input type="reset" value="重置信息"></td>
            </tr>
        </table>
    </form>
</body>
</html>
