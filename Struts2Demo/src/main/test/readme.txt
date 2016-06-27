
================= 操作步骤 ==============
1. 添加依赖 Struts2 --> 2.5.1(X) --> 2.3.1
2. 在 web.xml中, 添加过滤器
3. 添加struts.xml
4. 创建action --> 就是pojo 类
  - 4.1 为action创建exeute方法
  - 4.2 在struts.xml配置action 和返回结果集
5. OGNL -- object graghic navgation language

6. 创建 拦截器,继承AbstractInterceptor, invocation.invoke ->往下走
struts.xml里面配置

** 利用拦截器 实现权限控制 **

Role    --> admin
Article --> user
login   --> common

user/admin/commom_User_add -->

