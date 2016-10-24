<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>title</title>
</head>
<body>

<#include "/inc/top.ftl" />
    <#assign n =1>
    ${n+1}
<#assign s="1">
    ${s+1}
<#assign b = true >${b?string("yeas","no")} <#-- 将boolean转至string-->
${.now} -- ${.now?string("yyyy/MM/dd")}    <#--显示当前日期-->
<#assign d = "2016/12/22"?date("yyyy/MM/dd")>
date-- ${d?string("yyyy-MM-dd")}

</body>
</html>