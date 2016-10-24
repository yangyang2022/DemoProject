<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>已接受信件</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
</head>
<body>
    <jsp:include page="nav.jsp" />
    <table class="ct" width="900" align="center" border="1" cellspacing="0" cellpadding="0">
        <tr>
            <td colspan="5">
                <form action="message_listRecive" method="get">
                    <input type="hidden" name="isRead" value="${param.isRead}" />
                    关键字: <input type="text" name="con" /><input type="submit" value="搜索" />
                </form>
                <a href="message_listRecive?isRead=1">已读信件</a>
                <a href="message_listRecive?isRead=0">未读信件</a>
            </td>
        </tr>
        <tr>
            <td>消息ID</td>
            <td>消息标题</td>
            <td>发送者</td>
            <td>发送时间</td>
            <td>操作</td>
        </tr>
        <s:if test="#pages.totalRecord == 0 ">
            <tr>
                <td colspan="5">当前没有消息!</td>
            </tr>
        </s:if>
        <s:else>
        <s:iterator value="#pages.datas">
            <tr>
                <td>${id}</td>
                <td><a href="message_show?id=${id}&isRead=${isRead}">${title}</a></td>
                <td>${user.username}[${user.department.name}]</td>
                <td>${createDate}</td>
                <td>
                    <a href="message_deleteRecive?id=${id}">删除</a>
                </td>
            </tr>
        </s:iterator>
        <tr>
            <td colspan="5">
                <jsp:include page="/inc/pager.jsp">
                    <jsp:param name="url" value="message_listRecive" />
                    <jsp:param name="items" value="${pages.totalRecord}" />
                    <jsp:param name="params" value="con" />
                    <jsp:param name="params" value="isRead" />
                </jsp:include>
            </td>
        </tr>
    </table>
    </s:else>
</body>
</html>
