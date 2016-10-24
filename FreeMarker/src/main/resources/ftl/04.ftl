<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>title</title>
</head>
<body>
<#--
  多个模板文件里面有同名变量会覆盖
    解决办法: 使用import并且加入名称空间
-->
<#include "/inc/inc1.ftl" />
<#include "/inc/inc2.ftl" />

<#import "/inc/inc1.ftl" as inc1 />
<#import "/inc/inc2.ftl" as inc2 />

${name} -- ${inc1.name} -- ${inc2.name}

</body>
</html>