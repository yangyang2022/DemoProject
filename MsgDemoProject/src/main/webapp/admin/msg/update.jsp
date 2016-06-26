<%@ page import="DaoFactory" %>
<%@ page import="com.yangyang.dao.IMessageDao" %>
<%@ page import="com.yangyang.mode.Message" %>
<%@ page import="java.io.IOException" %>
<%@ page import="com.yangyang.mode.MsgException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    try {
        IMessageDao messageDao = DaoFactory.getMsgDao();
        int id = Integer.parseInt(request.getParameter("id"));
        Message msg = messageDao.load(id);

        String title = request.getParameter("title");
        String content = request.getParameter("content");

        msg.setTitle(title);
        msg.setContent(content);
        messageDao.update(msg);

        response.sendRedirect("/msg/list.jsp");
    } catch (MsgException e) {
%>
<h2>出现错误:<%=e.getMessage()%></h2>
<%
    } catch (Exception ee) {
%>
<h2>出现未知错误:<%=ee.getMessage()%></h2>
<%
    }

%>