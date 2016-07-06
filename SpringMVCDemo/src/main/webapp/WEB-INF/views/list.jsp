<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Emps List</title>

    <!--springmvc 处理静态资源  -->

</head>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
<script type="text/javascript">
    $(function(){
        $(".delete").click(function(){
            var href = $(this).attr("href");
            $("form").attr("action", href).submit();
            return false;
        });
    })
</script>
<body>
<form action="" method="post">
    <input type="hidden" name="_method" value="DELETE">
</form>
<a href="emps">List all employees</a>
<hr/>
 <c:if test="${empty emps}">没有任何员工信息</c:if>
<c:if test="${!empty emps}">
    <table border="1" cellpadding="10" cellspacing="0">
        <tr>
            <th>ID</th><th>LastName</th><th>Email</th><th>Gender</th><th>Department</th>
            <th>Edit</th><th>DELETE</th>
        </tr>
        <c:forEach items="${emps}" var="emp">
            <tr>
                <td>${emp.id}</td>
                <td>${emp.lastName}</td>
                <td>${emp.email}</td>
                <td>${emp.gender}</td>
                <td>${emp.department.depName}</td>
                <td><a  href="emp/${emp.id}">Edit</a></td>
                <td><a class="delete" href="emp/${emp.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<hr/>
    <a href="emp">Add new Employee</a>&nbsp;&nbsp;
</body>
</html>
