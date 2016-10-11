<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>用户列表</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
</head>
<body>
    <jsp:include page="nav.jsp" />
    <table class="ct" width="900" align="center" border="1" cellspacing="0" cellpadding="0">
        <tr>
            <td colspan="6">
                <form action="user_list" method="get">
                    <s:select theme="simple" list="#ds" listKey="id" listValue="name" value="depId" name="depId"
                    headerKey="" headerValue="全部部门"
                    />
                    <s:submit theme="simple" value="赛选" />
                </form>
            </td>
        </tr>
        <tr>
            <td>用户ID</td>
            <td>用户名称</td>
            <td>用户昵称</td>
            <td>用户邮箱</td>
            <td>用户类型</td>
            <td>所在部门</td>
            <td>操作</td>
        </tr>
        <s:if test="#pages.totalRecord == 0 ">
            <tr>
                <td colspan="7">当前没有用户!</td>
            </tr>
        </s:if>
        <s:else>
        <s:iterator value="#pages.datas">
            <tr>
                <td>${id}</td>
                <td><a href="user_show?id=${id}">${username}</a></td>
                <td>${nickname}</td>
                <td>${email}</td>
                <td><s:if test="type==0">普通用户</s:if><s:else>管理员</s:else> </td>
                <td>${department.name}</td>
                <td>
                    <a href="user_updateInput?id=${id}">更新</a>
                    <a href="user_delete?id=${id}">删除</a>
                </td>
            </tr>
        </s:iterator>
        <tr>
            <td colspan="7">
                <jsp:include page="/inc/pager.jsp">
                    <jsp:param name="url" value="user_list" />
                    <jsp:param name="items" value="${pages.totalRecord}" />
                    <jsp:param name="params" value="depId" />
                </jsp:include>
            </td>
        </tr>
    </table>
    </s:else>
</body>
</html>
