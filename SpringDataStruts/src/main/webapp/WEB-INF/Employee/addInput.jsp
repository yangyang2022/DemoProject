<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>添加员工</title>
    <script type="text/javascript" src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
    <script type="text/javascript">
        $(function () {
            $(":input[name=lastname]").change(function () {
                var val = $(this).val()
                var $this = $(this)

                val = $.trim(val)

                if(val != ""){
                    //清除所有的remove
                    $this.nextAll("font").remove();

                    var url = "Employee_validateLastName?lastname="+val;
                    var args = {"lastname":val,"time":new Date()};

                    $.get(url,args,function (data) {
                        if(data == "1"){
                            //可用
                            $this.after("<font color='aqua'>该名称可用!</font>")

                        }else{
                            //不可以
                            $this.after("<font color='red'>该名称不可用!</font>")
                        }
                    })
                }else{
                    alert("lastname 不能为空!")
                }
            })
        })
    </script>
</head>
<body>
<s:debug />
    <h2>Employee AddInput</h2>
    <s:form action="Employee_add" method="POST">
        <s:if test="id == null">
            <s:textfield name="lastname" label="lastName" />
        </s:if>
        <s:else>
            <s:hidden name="id" />
            <s:textfield name="lastname" label="lastName" disabled="true"/>
        </s:else>
        <s:textfield name="email"  label="Email" />
        <s:textfield name="born"  label="Born" />
        <s:select name="department.id" list="#deps" listKey="id"
                  listValue="departmentName" label="选择部门"
                  headerValue="选择一个部门" headerKey="-1"
                    value="1"
        />
        <s:submit value="添加用户"/>
    </s:form>
</body>
</html>
