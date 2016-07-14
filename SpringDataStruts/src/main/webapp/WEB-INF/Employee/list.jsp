<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Title</title>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
    <script>
        $(function () {
            //1: delete 时弹出 确定要删除xxx的信息吗?
            $(".delete").click(function () {
                var $tr = $(this).parent().parent()
                var lastName = $(this).next(":input").val()
                var flag = confirm("确定要删除 "+lastName+" 吗? ")
                if(flag){
                    //删除 使用ajax的方式
                    var url = this.href
                    var args = {"time":new Date()}
                    $.ajax({
                        url : url,
                        type : "POST",
                        data : "ajaxField=" + lastName,
                        success : function(data, textStatus) {
                            $tr.remove()
                            alert("删除成功!")
                        },
                        fail:function (data) {
                            alert("删除失败!")
                        }
                    });
                }

                //取消链接的默认行为
                return false;
            });
        })
    </script>
</head>
<body>
    <h1>Employee List</h1>
    <s:if test="#request.emps == null || #request.emps.size() == 0 ">
        没有员工信息
    </s:if>
    <s:else>
        <table border="1" cellspacing="0" cellpadding="10">
            <tr>
                <td>ID</td>
                <td>LastName</td>
                <td>Email</td>
                <td>Born</td>
                <td>CreateDate</td>
                <td>Department</td>
                <td>操作</td>
            </tr>
            <s:iterator value="#emps.getContent()" var="e">
                <tr>
                    <td>${e.id}</td>
                    <td>${e.lastname}</td>
                    <td>${e.email}</td>
                    <td>${e.born}</td>
                    <td>${e.createTime}</td>
                    <td>${e.department.departmentName}</td>
                    <td>
                        <a href="Employee_delete?id=${e.id}" class="delete">删除</a>&nbsp;
                        <input type="hidden" value="${e.lastname}">
                        <a href="Employee_addInput?id=${e.id}">更新</a>
                    </td>
                </tr>
            </s:iterator>
            <tr>
                <td colspan="7">
                    <jsp:include page="../inc/pager.jsp">
                        <jsp:param name="items" value="${emps.getTotalElements()}" />
                    </jsp:include>
                </td>
            </tr>
        </table>
    </s:else>
</body>
</html>
