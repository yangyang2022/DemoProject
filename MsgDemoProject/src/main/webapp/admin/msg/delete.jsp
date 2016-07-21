<%@ page import="com.yangyang.model.IMessageDao" %>
<%@ page import="DaoFactory" %>
<%@ page import="com.yangyang.Utils.MsgUtil" %>
<%@ page import="ValidateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    int id = Integer.parseInt(request.getParameter("id"));
    IMessageDao messgeDao = DaoFactory.getMsgDao();
    boolean flag = ValidateUtil.checkAdminOrSelf(session,messgeDao.load(id).getUserId());
    if(!flag){
        %>
    <h2>你没有权限删除该留言~</h2>
<%
    }else{
        messgeDao.delete(id);
        response.sendRedirect("/msg/list.jsp");
    }
%>