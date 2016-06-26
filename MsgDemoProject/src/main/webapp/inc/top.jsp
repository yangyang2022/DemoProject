<%@ page import="User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div style="text-align: right;border-bottom: 1px black solid">
<%
    User user = (User) session.getAttribute("loginUser");
    if(user != null){
        %>
    欢迎 [ <%=user.getUsername()%> ] 使用我们的系统! &nbsp;
    <a href="<%=request.getContextPath()%>/admin/user/list.jsp">用户管理</a>&nbsp;
    <a href="<%=request.getContextPath()%>/admin/user/updateSelfInput.jsp">修改个人信息</a>&nbsp;
    <a href="<%=request.getContextPath()%>/loginOut.jsp">退出系统</a>&nbsp;
<%
    }
%>
    <a href="<%=request.getContextPath()%>/msg/list.jsp">留言列表</a>&nbsp;
    <a href="<%=request.getContextPath()%>/loginInput.jsp">用户登录</a>&nbsp;
</div>