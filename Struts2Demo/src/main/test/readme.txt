
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

** 国际化问题 **
-- 通过不同的配置文件来指定相应的显示结果
    -- 中文 Message_zh_CN.properties
    -- 英文 Message_en_UK.properties

** struts 的国际化
    -- 局部国际化(很少)
    -- 基于包的国际化方案,在action所在的包中建立对应的package_cn.properties文件
    -- 创建 package_cn.properties / package_en.properties
    -- 让action extends ActionSupport
    -- 在页面中通过 <s:text name=message.id />
    -- 在连接着中加入 ?request_locale=en

