<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>商品类别更新</title>
</head>
<body>
<jsp:include page="inc.jsp" />
<form action="category?method=update" method="post">
    <input type="hidden" name="id" value="${category.id}">
    <table width="900" align="center" class="thin-border">
        <tr>
            <td>商品类别名称:<input type="text" name="name" value="${category.name}"><span class="errorContent">${errors.name}</span> </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="商品类别更新">&nbsp;
                <input type="reset" value="重置数据">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
