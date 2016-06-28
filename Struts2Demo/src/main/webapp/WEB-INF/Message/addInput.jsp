<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>Message addInput</title>
</head>
<s:debug />
<s:text name="hello" />
<body>
    <%--<form action="Message_add" method="post">--%>
        <%--<s:text name="message.id"/>: <input type="text" name="id" ><br/>--%>
        <%--<s:text name="message.title"/>:<input type="text" name="title" ><br/>--%>
        <%--<s:text name="message.content"/>:<input type="text" name="content" ><br/>--%>
        <%--<input type="submit" value="提交">--%>
    <%--</form>--%>
    <%-- struts 里面国际化 --%>
    <s:form action="Message_add" method="POST">
        <s:textfield key="message.id" name="id"/>
        <s:textfield key="message.title" name="title"/>
        <s:textfield key="message.content" name="content"/>
        <s:submit />
    </s:form>

<s:fielderror />
</body>
</html>
