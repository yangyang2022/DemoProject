<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>title</title>
</head>
<body>
<#-- 第一个是函数 以后都是参数 默认值的参数必须放在最后  -->
    <#macro hello world num=3 >
        <#list 1..num as n>
            hello:${username} -->${n} -->${world}
        </#list>
    </#macro>

<#-- 调用指令-->
    <@hello world="laoyang" />

<#-- 内嵌 -->
    <#macro test num =3>

        <#local username = "yangayng" />

        <ul>
            <#list 1..num as n>
                <#nested n>
            </#list>
        </ul>
    </#macro>
    <@test ;n>
        <li>${n}.abc</li>
    </@test>



</body>
</html>