<%@ page import="com.yangyang.model.IMessageDao" %>
<%@ page import="DaoFactory" %>
<%@ page import="com.yangyang.mode.Message" %>
<%@ page import="com.yangyang.Utils.MsgUtil" %>
<%@ page import="IUserDao" %>
<%@ page import="ValidateUtil" %>
<%@ page import="com.yangyang.model.ICommentDao" %>
<%@ page import="com.yangyang.mode.Pager" %>
<%@ page import="com.yangyang.mode.Comment" %>
<%@ page import="User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript" src="/xheditor/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/xheditor/xheditor-1.2.2.min.js"></script>
<script type="text/javascript" src="/xheditor/xheditor_lang/zh-cn.js"></script>
<%
    IMessageDao messageDao = DaoFactory.getMsgDao();
    IUserDao userDao = DaoFactory.getUserDao();
    ICommentDao commentDao = DaoFactory.getCommentDao();
    Pager<Comment> comPagers = null;
    User lu = (User) session.getAttribute("loginUser");

    Message msg = null;
    try {
        int id = Integer.parseInt(request.getParameter("msg_id"));
        msg = messageDao.load(id);
        comPagers = commentDao.list(id);

    } catch (Exception e) {
        %>
    <h2>出现错误: <%=e.getMessage()%></h2>
<%
    }
%>
<body>
<jsp:include page="inc.jsp">
    <jsp:param name="op" value="显示" />
</jsp:include>

<table width="900" align="center" border="1">
    <tr>
        <td><h3><%=msg.getTitle()%></h3></td>
    </tr>
    <tr>
        <td>
            <h4>发表日期: <%=MsgUtil.formateDate(msg.getPostTime())%>&nbsp;&nbsp;
            发布人员: <%=userDao.load(msg.getUserId()).getNickname()%>
            <%
                if(ValidateUtil.checkAdminOrSelf(session,msg.getUserId())){
                    %>
            <a href="/admin/msg/updateInput.jsp?id=<%=msg.getId()%>">更新</a>
            <a href="/admin/msg/delete.jsp?id=<%=msg.getId()%>">删除</a>
            <%
                }
            %>
            </h4>
        </td>
    </tr>
    <tr>
        <td>
            <%=msg.getContent()%>
        </td>
    </tr>
</table>
<table width="900" align="center" border="1">
    <%
        for(Comment com : comPagers.getDatas()){
            %>
    <tr>
        <td><%=com.getContent()%></td>
        <td><%=MsgUtil.formateDate(com.getPostDate())%>&nbsp;
            <%=userDao.load(com.getUserId()).getNickname()%> &nbsp;
            <%
                if(ValidateUtil.checkAdminOrSelf(session,com.getUserId())){
                    %>
            <a href="/admin/comment/delete.jsp?id=<%=com.getId()%>&msg_id=<%=msg.getId()%>">删除</a>
            <%
                }
            %>
        </td>
    </tr>
    <%
        }
    %>
    <tr>
        <td colspan="2">
            <jsp:include page="/inc/pager.jsp">
                <jsp:param name="items" value="<%=comPagers.getTotalRecord()%>" />
                <jsp:param name="params" value="msg_id" />
            </jsp:include>
        </td>
    </tr>
</table>
<%
    if(lu != null){
        %>
<form action="/admin/comment/add.jsp">
    <input type="hidden" name="id" value="<%=msg.getId()%>">
    <table width="900" align="center" border="1">
        <tr>
            <td>添加回复</td>
        </tr>
        <tr>
            <td>
                <textarea id="elm1" name="content" rows="10" cols="120" class="xheditor">

                </textarea>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="提交回复">
            </td>
        </tr>
    </table>
</form>
<%
    }
%>
</body>
</html>
