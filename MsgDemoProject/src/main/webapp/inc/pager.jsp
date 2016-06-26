<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>

<%
    int items = Integer.parseInt(request.getParameter("items"));
    String condition = request.getParameter("params");
    if(condition == null) condition = "";
    String[] ps = condition.split(",");
%>

<pg:pager maxPageItems="15" items="<%=items%>" export="number">
    <%
        for(String s:ps){
            %>
        <pg:param name="<%=s%>" />
    <%
        }
    %>
    <pg:last>
        共[ <%=items%> ] 条记录,共 [<%=pageNumber%>] 页
    </pg:last>
    当前[ <%=number%> ] 页
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
        [ <%=pageNumber%>]
        <%
        }else {
        %>
        <a href="<%=pageUrl%>">[<%=pageNumber%>]</a>
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