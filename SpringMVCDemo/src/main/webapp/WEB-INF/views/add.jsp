<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

    <form action="testConverse" method="post">
        <!-- lastName-email-gender-department.id 例如: 小莫-momo@qq.com-男-103 -->
        Employee: <input type="text" name="employee" />
        <input type="submit" value="Submit">
    </form>
    <%--
     1: 可以快速开发表单页面 而且 可以方便的 进行表单值得回现
     2: 注意会出现command exception,必须加入 modelAttribute = "xxx" ,这个在handler里面存的
     3: ${pageContext.request.contextPath}/emp 绝对路径

    --%>
<s:form action="${pageContext.request.contextPath}/emp" method="post" modelAttribute="employee">
    <%--<s:errors path="*"></s:errors>--%>
    <%--path --> 对应 name --%>
    <c:if test="${employee.id == null}">
        lastName:<s:input path="lastName" /><s:errors path="lastName" />
        <br/>
    </c:if>
    <c:if test="${employee.id != null}">
        lastName: ${employee.lastName} <br/>
        <s:hidden path="id" />
        <input type="hidden" name="_method" value="PUT">
    </c:if>
    email: <s:input path="email" /><s:errors path="email" />
    <br/>
    <%
        Map<String,String> genders = new HashMap<>();
        genders.put("男","male");
        genders.put("女","female");
        request.setAttribute("genders",genders);
    %>
    gender:<s:radiobuttons path="gender" items="${genders}"/><br/>
    department:<s:select path="department.id" items="${deps}" itemLabel="depName" itemValue="id"/>
    <br/>
    <!-- 1: 数据类型转换 2: 数据类型格式化 3:数据校验-->
    born: <s:input path="born" /><s:errors path="born" />
    <br/>
    salary:<s:input path="salary" /><s:errors path="salary" />
    <br/>
    <input type="submit" value="Submit">
</s:form>
</body>
</html>
