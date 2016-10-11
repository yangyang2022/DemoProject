<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>title</title>
</head>
<body>
    <#import "/inc/select.ftl" as my />
    <@my.select id="choice" datas=["张学友","刘德华","洋洋"] value="洋洋"></@my.select>
    <@my.select id="address" datas=["北京","上海","天景"] headkey="-1" headvalue="选择一个西区"></@my.select>
    <@my.select id="emps" datas=emps key="id" text="name" value="1" headkey="-1" headvalue="请选择数据"></@my.select>
    <@my.select id="sexs" datas={"0":"男","1":"女"} value="1" headkey="-1" headvalue="请选择性别"></@my.select>
</body>
</html>