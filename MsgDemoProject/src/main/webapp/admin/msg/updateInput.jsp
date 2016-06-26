<%@ page import="User" %>
<%@ page import="com.yangyang.dao.IMessageDao" %>
<%@ page import="DaoFactory" %>
<%@ page import="com.yangyang.mode.Message" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Message</title>
    <script type="text/javascript" src="/xheditor/jquery/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="/xheditor/xheditor-1.2.2.min.js"></script>
    <script type="text/javascript" src="/xheditor/xheditor_lang/zh-cn.js"></script>
</head>
<%
    User lu = (User) session.getAttribute("loginUser");
    IMessageDao messageDao = DaoFactory.getMsgDao();
    int id = Integer.parseInt(request.getParameter("id"));
    Message msg = messageDao.load(id);
%>
<body>
<form action="update.jsp" method="post">
    <input type="hidden" name="id" value="<%=msg.getId()%>">
    <table width="800" align="center" border="1">
        <tr>
            <td>标题</td>
            <td><input type="text" name="title" size="100" value="<%=msg.getTitle()%>"></td>
        </tr>
        <tr>
            <td colspan="2">内容</td>
        </tr>
        <tr>
            <td colspan="2">
                <textarea id="elm1" name="content" class="xheditor" rows="30" cols="120">
                    <%=msg.getContent()%>
                </textarea>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="更新留言">&nbsp;
                <input type="reset" value="重置数据" >
            </td>
        </tr>
    </table>
</form>
</body>
</html>
