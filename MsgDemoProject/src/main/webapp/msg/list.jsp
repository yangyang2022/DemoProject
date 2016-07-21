<%@ page import="com.yangyang.model.ICommentDao" %>
<%@ page import="DaoFactory" %>
<%@ page import="com.yangyang.mode.Pager" %>
<%@ page import="com.yangyang.mode.Comment" %>
<%@ page import="com.yangyang.mode.Message" %>
<%@ page import="com.yangyang.model.IMessageDao" %>
<%@ page import="java.util.List" %>
<%@ page import="com.yangyang.Utils.MsgUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Comment List</title>
</head>
<%
    IMessageDao messageDao = DaoFactory.getMsgDao();
    Pager<Message> pagers = messageDao.list();
    int totalRecord = pagers.getTotalRecord();
%>
<body>
<jsp:include page="inc.jsp">
    <jsp:param name="op" value="添加" />
</jsp:include>
    <table align="center" width="900" border="1">
        <tr>
            <td>标题</td>
            <td>发布时间</td>
            <td>发布人</td>
            <td>评论数量</td>
        </tr>
        <%
            for(Message msg:pagers.getDatas()){
                %>
            <tr>
                <td><a href="show.jsp?msg_id=<%=msg.getId()%>"><%=msg.getTitle()%></a></td>
                <td><%=MsgUtil.formateDate(msg.getPostTime())%></td>
                <td><%=msg.getUserId()%></td>
                <td><%=messageDao.getCommentCount(msg.getId())%></td>
            </tr>
        <%
            }
        %>
        <tr>
            <td colspan="4">
                <jsp:include page="/inc/pager.jsp">
                    <jsp:param name="items" value="<%=totalRecord%>" />
                </jsp:include>
            </td>
        </tr>
    </table>
</body>
</html>
