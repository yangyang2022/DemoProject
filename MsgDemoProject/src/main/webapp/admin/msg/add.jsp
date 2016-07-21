<%@ page import="com.yangyang.model.IMessageDao" %>
<%@ page import="DaoFactory" %>
<%@ page import="com.yangyang.mode.Message" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.yangyang.mode.MsgException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    try {
        IMessageDao messageDao = DaoFactory.getMsgDao();
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        int user_id = Integer.parseInt(request.getParameter("user_id"));

        Message msg = new Message(title,content,new Date(),user_id);
        messageDao.add(msg,user_id);
        response.sendRedirect("/msg/list.jsp");
    } catch (MsgException e) {
        %>
    <h2>出现错误: <%=e.getMessage()%></h2>
    <%
        } catch (Exception ee) {
            %>
    <h2>出现未知错误: <%=ee.getMessage()%></h2>
    <%
        }
    %>