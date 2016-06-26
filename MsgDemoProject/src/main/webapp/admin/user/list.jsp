<%@ page import="IUserDao" %>
<%@ page import="DaoFactory" %>
<%@ page import="java.util.List" %>
<%@ page import="User" %>
<%@ page import="com.yangyang.mode.Pager" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List All Users</title>
</head>
<%
    String con = request.getParameter("con");
    if(con == null) con = "";

    IUserDao userDao = DaoFactory.getUserDao();
    Pager<User> pagers = userDao.list(con);

    int pageIndex = pagers.getPageIndex();
    int pageSize = pagers.getPageSize();

    List<User> users = pagers.getDatas();

    User loginUser = (User) session.getAttribute("loginUser");

    String admin_user = "管理员";
    String ord_user = "普通用户";
    String open_state = "启用";
    String stop_state = "<span style='color:red'>禁用</span>";
%>
<body>
<jsp:include page="inc.jsp">
    <jsp:param name="op" value="列表" />
</jsp:include>
    <table align="center" border="1" width="1000">
        <tr>
            <td colspan="7">
               <form action="list.jsp">
                   输入用户名或昵称: <input type="text" name="con" value="<%=con%>"/>&nbsp;
                   <input type="submit" value="提交">
               </form>
            </td>
        </tr>
        <tr>
            <td>用户标识</td><td>用户姓名</td><td>用户密码</td><td>用户昵称</td>
            <td>用户类型</td><td>用户状态</td>
            <td colspan="2" align="center">操作</td>
        </tr>
            <%
                for(User user:users){
                    %>
                <tr>
                    <td><%=user.getId()%></td>
                    <td><%=user.getUsername()%></td>
                    <td><%=user.getPassword()%></td>
                    <td><%=user.getNickname()%></td>
                    <td>
                        <%
                            if(user.getType() == 0 ) {
                                %>
                            <%=ord_user%> <%
                                if(loginUser.getType() == 1){
                                    %>
                                    <a href="setType.jsp?id=<%=user.getId()%>">设置管理员</a>
                            <%
                                }
                            %>
                        <%
                            }
                            else {
                                %>
                            <%=admin_user%> <%
                                if(loginUser.getType() == 1){
                                    %>
                                <a href="setType.jsp?id=<%=user.getId()%>">取消管理员</a>
                                <%
                                }
                            %>
                        <%
                            }
                        %>
                    </td>
                    <td>
                        <%
                            if(user.getStatus() == 0 ) {
                                %>
                            <%=open_state%> <%
                                if(loginUser.getType() == 1){
                                    %>
                        <a href="setStatus.jsp?id=<%=user.getId()%>">停用</a>
                            <%
                                }
                            %>
                        <%
                            }
                            else {
                                %>
                            <span style="color: red"><%=stop_state%></span>
                            <%
                                if(loginUser.getType() == 1){
                                    %>
                        <a href="setStatus.jsp?id=<%=user.getId()%>">启用</a>
                        <%
                                }
                            %>
                        <%
                            }
                        %>
                    </td>
                    <td><a href="delete.jsp?id=<%=user.getId()%>">删除</a>&nbsp;
                    <a href="updateInput.jsp?id=<%=user.getId()%>">更新</a></td>
                </tr>
            <%
                }
            %>
        <tr>
            <td colspan="8">
                <jsp:include page="/inc/pager.jsp">
                    <jsp:param name="items" value="<%=pagers.getTotalRecord()%>" />
                    <jsp:param name="params" value="con,type,status" />
                </jsp:include>
            </td>
        </tr>
    </table>
</body>
</html>
