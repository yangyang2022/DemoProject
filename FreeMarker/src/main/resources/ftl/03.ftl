<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>title</title>
</head>
<body>
<#include "/inc/top.ftl" />
<#-- username 是和 top.ftl里面有冲突-->
<#--
    freemarker 里面的变量类型:
    1: 数据模型变量 -- root里面的数据
    2: 模板变量 -- 通过<#assign 的变量
    3: 局部变量
    4: 循环变量

    当在模板中显示变量时: -- 首先在模板中寻找 然后到数据模型中(root)寻找
    如果直接去root中找 .globals.username
-->
<#assign username="heheh">
username: ${.globals.username}

</body>
</html>