<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>已发送信件</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
</head>
<body>
    <jsp:include page="nav.jsp" />
    <table class="ct" width="900" align="center" border="1" cellspacing="0" cellpadding="0">
        <tr>
            <td colspan="4">
                <form action="message_listSend" method="get">
                    关键字: <input type="text" name="con" value="检索" /><input type="submit" value="搜索" />
                </form>
            </td>
        </tr>
        <tr>
            <td>消息ID</td>
            <td>消息标题</td>
            <td>发送时间</td>
            <td>操作</td>
        </tr>
        <s:if test="#pages.totalRecord == 0 ">
            <tr>
                <td colspan="4">当前没有消息!</td>
            </tr>
        </s:if>
        <s:else>
        <s:iterator value="#pages.datas">
            <tr>
                <td>${id}</td>
                <td><a href="message_show?id=${id}&isRead=1">${title}</a></td>
                <td>${createDate}</td>
                <td>
                    <a href="message_deleteSend?id=${id}">删除</a>
                </td>
            </tr>
        </s:iterator>
        <tr>
            <td colspan="4">
                <jsp:include page="/inc/pager.jsp">
                    <jsp:param name="url" value="message_listSend" />
                    <jsp:param name="items" value="${pages.totalRecord}" />
                    <jsp:param name="params" value="con" />
                </jsp:include>
            </td>
        </tr>
    </table>
    </s:else>
</body>
</html>
