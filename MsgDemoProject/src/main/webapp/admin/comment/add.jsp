<%@ page import="com.yangyang.model.ICommentDao" %>
<%@ page import="DaoFactory" %>
<%@ page import="com.yangyang.mode.Comment" %>
<%@ page import="java.util.Date" %>
<%@ page import="User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    int msg_id = Integer.parseInt(request.getParameter("id"));
    String content = request.getParameter("content");

    ICommentDao commentDao = DaoFactory.getCommentDao();
    Comment comment = new Comment(content,new Date());
    int user_id = ((User)session.getAttribute("loginUser")).getId();
    commentDao.add(comment,user_id,msg_id);
    response.sendRedirect("/msg/show.jsp?msg_id="+msg_id);
%>