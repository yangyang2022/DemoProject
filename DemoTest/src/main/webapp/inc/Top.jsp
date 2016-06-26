<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<a href="http://#">网站首页</a>&nbsp;
<a href="http://#">就业导向</a>&nbsp;
<a href="http://#">招生信息</a>&nbsp;
<a href="http://#">校长信息</a>&nbsp;
<hr/>

<%--
    动态包含: 将要导入的文件 原样导入
    静态包含: 编译过后 导入
    开发中使用 : 90% 使用静态包含
    动态包含 容易 出现变量名重复的问题
--%>
<%=application.getRealPath("/")%>
<%
    String str = "hello ";
%>