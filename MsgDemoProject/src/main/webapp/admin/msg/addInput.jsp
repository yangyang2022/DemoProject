<%@ page import="User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Message</title>
</head>
<%
    User lu = (User) session.getAttribute("loginUser");
%>
<script type="text/javascript" src="/xheditor/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/xheditor/xheditor-1.2.2.min.js"></script>
<script type="text/javascript" src="/xheditor/xheditor_lang/zh-cn.js"></script>
<body>
<form action="add.jsp" method="post">
    <input type="hidden" name="user_id" value="<%=lu.getId()%>">
    <table width="800" align="center" border="1">
        <tr>
            <td>标题</td>
            <td><input type="text" name="title" size="100"></td>
        </tr>
        <tr>
            <td colspan="2">内容</td>
        </tr>
        <tr>
            <td colspan="2">
                <textarea id="elm1" name="content" class="xheditor" rows="30" cols="120">

                </textarea>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="添加留言">&nbsp;
                <input type="reset" value="重置数据" >
            </td>
        </tr>
    </table>
</form>
</body>
</html>
