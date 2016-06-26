<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/inc/top.jsp" />
<h2 align="center">留言 <%=request.getParameter("op")%> 功能</h2>
<a href="../admin/msg/addInput.jsp">添加留言</a>
<a href="<%=request.getContextPath()%>/msg/list.jsp">留言列表</a>&nbsp;
<a href="http://">备用</a>&nbsp;
<a href="http://">备用</a>&nbsp;
<hr>