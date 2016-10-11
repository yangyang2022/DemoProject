<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>title</title>
</head>
<body>

${doc.fields.field[0].@id} -- ${doc.fields.field[0].@name}

<#list doc["fields/field[@id='nation']/data"] as d>
    ${d.key}--${d.value}
</#list>

</body>
</html>