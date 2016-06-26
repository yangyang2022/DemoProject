<%@ page import="com.yangyang.dao.ICommentDao" %>
<%@ page import="DaoFactory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    int id = Integer.parseInt(request.getParameter("id"));
    int msg_id = Integer.parseInt(request.getParameter("msg_id"));
    ICommentDao commentDao = DaoFactory.getCommentDao();
    commentDao.delete(id);
    response.sendRedirect("/msg/show.jsp?msg_id="+msg_id);
%>