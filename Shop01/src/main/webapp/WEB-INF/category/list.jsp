<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>商品类别列表</title>
    <style type="text/css">
        span.category{
            color: aliceblue;
        }
        a.category_link:link{
            background: grey;
            padding: 5px;
            margin: 10px;
            text-decoration: none;
        }
        a.category_link:hover{
            background: red;
        }
        #container{
            width: 900px;
            border: 1px solid;
            padding: 10px;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<jsp:include page="inc.jsp" />
<form action="category?method=list" method="post">
    <hr/>
    输入筛选条件:<input type="text" name="good_name" value="${param.good_name}">
    <input type="submit" value="搜索"><br/>

    <div id = "container" align="center">
        <c:if test="${empty cs}">没有任何商品分类</c:if>
        <c:if test="${not empty cs}">
            <c:forEach items="${cs}" var="category">
                <a class="category_link" href="category?method=show&id=${category.id}">${category.name}</a>
            </c:forEach>
        </c:if>
    </div>
</form>
</body>
</html>
