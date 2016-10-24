<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>查询信件</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
    <script src="//cdn.ckeditor.com/4.5.10/standard/ckeditor.js"></script>
</head>
<body>
    <jsp:include page="nav.jsp" />
    <br/>
    <table class="ct" width="900" align="center" border="1" cellspacing="0" cellpadding="0">
        <tr>
            <td>消息ID</td><td>${id}</td>
        </tr>
        <tr>
            <td>消息标题</td><td>${title}</td>
        </tr>
        <tr>
            <td>发送者</td><td>${user.username}</td>
        </tr>
        <tr>
            <td>发送时间</td><td>${createDate}</td>
        </tr>
        <tr>
            <td>附件:</td>
            <td>
                <s:if test="#atts.size() <= 0">没有附件</s:if>
                <s:else>
                    <s:iterator value="#atts">
                        ${oldName} ,
                    </s:iterator>
                </s:else>
            </td>
        </tr>
        <tr><td colspan="2">消息内容</td></tr>
        <tr>
            <td colspan="2">
                <textarea cols="125" rows="20" name="editor1" disabled="true">${content}</textarea>
                <script>CKEDITOR.replace( 'editor1' );</script>
            </td>
        </tr>
    </table>
</body>
</html>
