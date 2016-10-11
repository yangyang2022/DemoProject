<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>title</title>
</head>
<body>
    <#--<h1>hello html ${emp.id} -- ${emp.username} -- ${emp.age}</h1>-->
    <#-- 这里是注释 一下是判断 -->
    <#--<#if emp.age < 18>-->
        <#--${emp.name}是童工-->
    <#--<#elseif emp.age gt 60>-->
        <#--${emp.name} 该退休啦-->
    <#--<#else >-->
        <#--${emp.name} 好好上班-->
    <#--</#if>-->

<#list emps as emp>
    ${emp.id} -- ${emp.name} -- ${emp.age} <br/>
</#list>


</body>
</html>