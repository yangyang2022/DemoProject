<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title><decorator:title default="欢迎使用网上商城" /></title>
    <link rel="stylesheet" href="/css/main.css">
    <decorator:head />
</head>
<body>
<c:if test="${not empty loginUser}">
    欢迎[${loginUser.nickname}]登录系统 &nbsp;
    <a href="/user?method=updateSelfInput">修改个人信息</a>&nbsp;
    <a href="/user?method=show&id=${loginUser.id}">个人信息查询</a>&nbsp;
    <a href="/user?method=logout">用户退出</a>&nbsp;
</c:if>
<c:if test="${empty loginUser}">
    欢迎[ 游客 ]登录系统 &nbsp;
    <a href="/user?method=loginInput">用户登录</a>&nbsp;
    <a href="/user?method=addInput">用户注册</a>&nbsp;
</c:if>
<a href="/category">商品类别管理</a>&nbsp;
<a href="/product">商品管理</a>&nbsp;
<a href="/user">用户管理</a>&nbsp;

<hr/>
<h3 align="center"><decorator:title default="商城管理"/></h3>
<decorator:body />
<hr/>
<div align="center">
    CopyRight@2012-2016<br/>
    网上商城测试项目
</div>
</body>
</html>
