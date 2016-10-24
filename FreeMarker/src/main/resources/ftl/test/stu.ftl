<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>title</title>
</head>
<body>

<#import "/inc/select.ftl" as my>

<#list filterDoc["filters/filter[@id='${obj}']/field"] as f>
    <@my.xmlSelect cid="${f.@cid}" id="${f.@id}" />
</#list>
</body>
</html>