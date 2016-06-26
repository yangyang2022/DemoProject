<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>商品添加</title>
</head>
<body>
<jsp:include page="inc.jsp" />
<form action="product?method=add" method="post">
    <table width="900" align="center" class="thin-border">
        <tr>
            <td>商品名称:</td><td> <input type="text" name="name" value="${param.name}"><span class="errorContent">${errors.name}</span> </td>
        </tr>
        <tr>
            <td>商品价格:</td><td> <input type="text" name="price" value="${param.price}"><span class="errorContent">${errors.price}</span> </td>
        </tr>
        <tr>
            <td>商品库存:</td><td> <input type="text" name="stock" value="${param.stock}"><span class="errorContent">${errors.stock}</span> </td>
        </tr>
        <tr>
            <td>商品类别:</td><td>
            <select name="cid">
                <option value="">请选择商品类别</option>
                <c:forEach items="${cs}" var="c">
                    <c:if test="${c.id == param.cid}">
                        <option value="${c.id}" selected>${c.name}</option>
                    </c:if>
                    <c:if test="${c.id != param.cid}">
                        <option value="${c.id}">${c.name}</option>
                    </c:if>
                </c:forEach>
            </select>
            <span class="errorContent">${errors.cid}</span> </td>
        </tr>
        <tr>
            <td>商品图片</td>
            <td>
                <input type="file" name="img" value="${param.img}">
             </td>
        </tr>
        <tr>
            <td colspan="2">商品介绍</td>
        </tr>
        <tr>
            <td colspan="2">
                <textarea cols="124" rows="14">${param.intro}</textarea>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="添加商品">&nbsp;
                <input type="reset" value="重置数据">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
