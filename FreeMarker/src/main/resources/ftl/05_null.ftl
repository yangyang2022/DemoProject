<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>title</title>
</head>
<body>
<#-- freemarker必须要自己处理空值 !("xxx")-->
id: ${emp.id} -- group: ${emp.group!("没有group")} -- ${(emp.group.name)!("没有名称")}

${("a.b.c")!("a.b没有定义")}

<#-- 判断a.b 是否为空 -->
<#if (a.b) ?? >
    不为空
<#else >
    为空
</#if>

<#-- 判断(a.b)是否为空 -->
${(a.b)???string}
</body>
</html>