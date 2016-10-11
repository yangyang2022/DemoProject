<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title><decorator:title default="欢迎使用文档管理系统" /></title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
    <decorator:head />
</head>
<body>

<s:if test="#session.loginUser != null">
    欢饮[${loginUser.username}]登录!
    <s:if test="#session.loginUser.type==1">
        <a href="<%=request.getContextPath()%>/user_list">用户管理</a>
        <a href="<%=request.getContextPath()%>/department_list">部门管理</a>
        <a href="<%=request.getContextPath()%>/user_list">用户管理</a>
    </s:if>
    <a href="<%=request.getContextPath()%>/message_listRecive">私人信件管理</a>
    <a href="<%=request.getContextPath()%>/user_updateSelfInput">修改个人信息</a>
    <a href="<%=request.getContextPath()%>/user_showSelf">个人信息查询</a>
    <a href="<%=request.getContextPath()%>/login!logout">退出系统</a>
</s:if>
<s:else>
    <a href="<%=request.getContextPath()%>/login!loginInput">用户登录</a>
</s:else>

<hr/>
<h2 align="center"><decorator:title default="文档管理系统"/></h2>
<decorator:body />
<div align="center" style="width: 100%;border-top: 1px solid;float: left;margin-top: 10px">
    CopyWrite@201-2016
    开发测试项目
</div>
</body>
</html>
