<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>title</title>
</head>
<body>
    <#assign nums = [1,2,3,4,5] />
    <#list nums as num>
        ${num}
    </#list>

    <#-- freemarker 特殊的一点,对于map而言,只支持string作为key -->
    <#assign map={"1":"yangyang","2":"hello world"} />
    ${map["2"]}

    <#-- map的遍历-->
    <#assign keys=map?keys />
    <#list keys as key>
        ${key} -- ${map["${key}"]}
    </#list>

</body>
</html>