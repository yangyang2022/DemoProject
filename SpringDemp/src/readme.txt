** spring 的配置步骤  **
1 -- 导入jar包
2 -- 创建beans.xml 添加相应的schema
3 -- 创建spring工厂

** 静态代理 **
 -- userDao --> UserProxyDao
 -- messageDao --> MessageProxyDao

** 动态代理的实现 **
 -- 通过一个代理对象来创建需要的 业务对象,然后在这个代理对象中统一
 -- 进行各种需求的处理

 步骤:   1 创建一个类 实现invocationHandler 接口
        2 创建要代理的对象

** spring AOP 动态代理的的实现 **
 -- 在xml中打开 aop-autoproxy
 -- 建立 切面类 LogAspect
