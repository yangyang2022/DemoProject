<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <pg:pager items="1000" maxPageItems="20" maxIndexPages="20" export="number">
        <pg:first>
            <a href="<%=pageUrl%>">首页</a>
        </pg:first>
        <pg:prev>
            <a href="<%=pageUrl%>">上一页</a>
        </pg:prev>
        <pg:pages>
            <%
                if(pageNumber == number){
                    %>
                    [<%=pageNumber%>]
            <%
                }else {
                    %>
            <a href="<%=pageUrl%>"><%=pageNumber%></a>
            <%
                }
            %>
        </pg:pages>
        <pg:next>
            <a href="<%=pageUrl%>">下一页</a>
        </pg:next>
        <pg:last>
            <a href="<%=pageUrl%>">尾页</a>
        </pg:last>
    </pg:pager>
</body>
</html>
