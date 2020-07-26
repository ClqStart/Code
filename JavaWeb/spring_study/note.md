## 常用的依赖
```xml
<!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
  <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>5.2.2.RELEASE</version>
  </dependency>
  <!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
  <dependency>
      <groupId>org.springframework</groupId>
     <artifactId>spring-jdbc</artifactId>
  <version>5.2.2.RELEASE</version> </dependency>
```
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">

</beans>
```
##开启注解支持
```xml
<!--扫描包-->
<context:component-scan base-package="com.kuang.pojo"/>
<!--开启支持-->
<context:annotation-config/>
```
##注解说明
- @Autowired:自动装配通过类型，名字
      如果Autowired不能唯一装配上属性，则需要通过@Qualifier(value="XXXX"")
- @Resource:自动装配通过名字，类型。
- @Nullable:字段标记这个注解，说明这个注解可以为null.  

- @Component: 组件，放在类上，说明这个类被Spring管理了，就是bean.

##代理模式
###角色分析
- 抽象角色:一般使用接口或者抽象类来解决
- 真是角色：被代理的角色
- 代理的角色：代理真实角色，代理角色后，我们一般会做一些附属工作
- 客户： 访问代理对象的人
##动态代理
    - 动态代理和静态代理角色一样
    - 动态代理的代理类是动态生成的，不是直接写好的
    - 动态代理分为两大类：基于接口的动态代理，基于类的动态代理
        -- 基于接口 ---JDK动态代理
        -- 基于类: cglib
        --java字节码实现：javasist
        两个类：
###mybatis 依赖
```xml
 <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.47</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.2</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.1.9.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.1.9.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.8.13</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-spring -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>2.0.3</version>
        </dependency>

    </dependencies>
```
##声明式事务
- 事务ACID原则

   -  原子性
   - 一致性
   - 隔离性：对个业务操作同一个资源，防止数据破坏
   - 持久性： 无论系统发生什么问题，结果都不会影响，被持久化
              在储器中。
##编程式事务