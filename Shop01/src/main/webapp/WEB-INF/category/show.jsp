<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>商品类别查询</title>
</head>
<body>
<jsp:include page="inc.jsp" />
<table width="900" align="center" class="thin-border">
    <tr>
        <td>商品类别名称:&nbsp;${category.name}</td>
    </tr>
    <tr>
        <td>
            <a href="category?method=updateInput&id=${category.id}">修改</a>&nbsp;
            <a href="category?method=delete&id=${category.id}">删除</a>
        </td>
    </tr>
</table>
</body>
</html>
