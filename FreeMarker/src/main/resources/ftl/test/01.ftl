<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>title</title>
</head>
<body>
<#--
<#assign ages = {"yangyang":30,"hehe":23}+{"yangyang":55,"lala":23}>
yangang is: ${ages.yangyang} -- ${ages["yangyang"]}
hehe is: ${ages.hehe} -- ${ages["hehe"]}
lala is: ${ages.lala} -- ${ages["lala"]}

1.1?int ${1.1?int}
yangyang.upper_case: ${"yangyang"?upper_case}
yangyang.upper_case: ${"yangyang"?cap_first}
yangyang.upper_case: ${"yangyang"?capitalize?html}
yangyang.size: ${"yangyang"?length}

${mouse!("no mouse")}
<#assign mouse="mouse">
${mouse!("no mouse")}

<#assign name = "hello ${name}">
${name} -- ${1.5}

<#macro greet name="" age=0>
    <font size="+2">Hello ${name}, age is: ${age}</font>
</#macro>

<@greet age=12 name= "${name}" />

<#macro do_three>
    <#nested 1>
    <#nested 2>
    <#nested 3>
</#macro>

<@do_three ;x>
    ${x} do_something ...
</@do_three>
<#macro repeat count>
    <#list 1..count as x>
        <#nested x,x/2,x==count>
    </#list>
</#macro>
<@repeat count=4;c,halfc,last>
    ${c}.${halfc} <#if last>Last!</#if>
</@repeat>

<#list ["hello"] as x>
    ${x}
</#list>

<#import "copyright.ftl" as my>

<@my.copyright date="2012-12-12"></@my.copyright>
${my.email}

<#assign email="xxx@gmail.com" in my>
${my.email}

<#compress >
<p>List all users </p>
<#assign users = [{"name":"joe","hidden":false},
            {"name":"james","hidden":true},
            {"name":"julia","hidden":false}] >

<ul>
    <#list users as user>
        <#if !user.hidden>
            <li>${user.name}</li>
        </#if>
    </#list>
</ul>
</#compress>

-->

<#assign maps = {"key1":"value1","key2":"value2","key3":"value3"}>
<#list maps?values as value>
    ${value}
</#list>
<#list maps?keys as key>
    <#--${maps.key}--> ${maps[key]}
</#list>

</body>
</html>