<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>商品类别添加</title>
</head>
<body>
<jsp:include page="inc.jsp" />
<form action="category?method=add" method="post">
    <table width="900" align="center" class="thin-border">
        <tr>
            <td>商品类别名称<input type="text" name="name" value="${param.name}"><span class="errorContent">${errors.name}</span> </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="商品类别添加">&nbsp;
                <input type="reset" value="重置数据">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
