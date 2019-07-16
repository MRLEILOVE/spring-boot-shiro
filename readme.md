## 主要框架

![SpringBoot version](https://img.shields.io/badge/SpringBoot-2.1.6.RELEASE-blue.svg)&nbsp;
![Shiro-Spring version](https://img.shields.io/badge/shiro--spring-1.4.0-orange.svg)&nbsp;
![druid version](https://img.shields.io/badge/druid-1.0.29-red.svg)&nbsp;
![mybatis plus version](https://img.shields.io/badge/mybatis--plus-3.1.1-green.svg)&nbsp;
![shiro-redis version](https://img.shields.io/badge/shiro--redis-3.1.0-yellow.svg)&nbsp;

## Shiro架构图与基本知识

1、Shiro是Apache下的一个开源项目，我们称之为Apache Shiro。它是一个很易用与Java项目的的安全框架，提供了认证、授权、加密、会话管理，与spring Security 一样都是做一个权限的安全框架，但是与Spring Security 相比，在于 Shiro 使用了比较简单易懂易于使用的授权方式。shiro属于轻量级框架，相对于security简单的多，也没有security那么复杂。所以我这里也是简单介绍一下shiro的使用。

2、非常简单；其基本功能点如下图所示：

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190706152247236.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM0ODQ1Mzk0,size_16,color_FFFFFF,t_70)

**Authentication**：身份认证/登录，验证用户是不是拥有相应的身份；

**Authorization**：授权，即权限验证，验证某个已认证的用户是否拥有某个权限；即判断用户是否能做事情，常见的如：验证某个用户是否拥有某个角色。或者细粒度的验证某个用户对某个资源是否具有某个权限；

**Session Manager**：会话管理，即用户登录后就是一次会话，在没有退出之前，它的所有信息都在会话中；会话可以是普通JavaSE环境的，也可以是如Web环境的；

**Cryptography**：加密，保护数据的安全性，如密码加密存储到数据库，而不是明文存储；

**Web Support**：Web支持，可以非常容易的集成到Web环境；

Caching：缓存，比如用户登录后，其用户信息、拥有的角色/权限不必每次去查，这样可以提高效率；

**Concurrency**：shiro支持多线程应用的并发验证，即如在一个线程中开启另一个线程，能把权限自动传播过去；

Testing：提供测试支持；

**Run As**：允许一个用户假装为另一个用户（如果他们允许）的身份进行访问；

**Remember Me**：记住我，这个是非常常见的功能，即一次登录后，下次再来的话不用登录了。

**记住一点，Shiro不会去维护用户、维护权限；这些需要我们自己去设计/提供；然后通过相应的接口注入给Shiro即可。**

## 源码地址
https://github.com/MRLEILOVE/spring-boot-shiro

## 数据库结构
5张表，也就是现在流行的权限设计模型[RBAC](https://baike.baidu.com/item/RBAC/1328788?fr=aladdin)，建表SQL已放在项目中。

用户、角色、权限、用户-角色、角色-权限，关系如下。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190706150822192.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM0ODQ1Mzk0,size_16,color_FFFFFF,t_70)


## 项目结构
下面是整个项目结构，主要类已做注释。
```
├─main
│  ├─java
│  │  └─com
│  │      └─leigq
│  │          └─www
│  │              └─shiro
│  │                  │  SpringBootShiroApplication.java
│  │                  │
│  │                  ├─bean
│  │                  │      CacheUser.java --- 缓存用户信息
│  │                  │      Response.java  --- 统一返回结果
│  │                  │
│  │                  ├─config
│  │                  │      DruidDataSourceConfig.java --- Druid数据源配置
│  │                  │      DruidMonitorConfig.java    --- Druid监控配置
│  │                  │      MyBatisPlusConfig.java     --- MyBatisPlus配置
│  │                  │      MySessionManager.java      --- 自定义session管理
│  │                  │      MyShiroRealm.java          --- 自定义 shiroRealm, 主要是重写其认证、授权
│  │                  │      ShiroConfig.java           --- Shiro管理
│  │                  │
│  │                  ├─controller
│  │                  │      LoginController.java
│  │                  │      PermissionController.java
│  │                  │      RoleController.java
│  │                  │      RolePermissionController.java
│  │                  │      UserController.java
│  │                  │      UserRoleController.java
│  │                  │
│  │                  ├─domain
│  │                  │  ├─entity
│  │                  │  │      Permission.java
│  │                  │  │      Role.java
│  │                  │  │      RolePermission.java
│  │                  │  │      User.java
│  │                  │  │      UserRole.java
│  │                  │  │
│  │                  │  └─mapper
│  │                  │          PermissionMapper.java
│  │                  │          RoleMapper.java
│  │                  │          RolePermissionMapper.java
│  │                  │          UserMapper.java
│  │                  │          UserRoleMapper.java
│  │                  │
│  │                  ├─service
│  │                  │  │  IPermissionService.java
│  │                  │  │  IRolePermissionService.java
│  │                  │  │  IRoleService.java
│  │                  │  │  IUserRoleService.java
│  │                  │  │  IUserService.java
│  │                  │  │
│  │                  │  └─impl
│  │                  │          PermissionServiceImpl.java
│  │                  │          RolePermissionServiceImpl.java
│  │                  │          RoleServiceImpl.java
│  │                  │          UserRoleServiceImpl.java
│  │                  │          UserServiceImpl.java
│  │                  │
│  │                  ├─util
│  │                  │      CodeGeneratorUtils.java --- MyBatisPlus代码生成器
│  │                  │
│  │                  └─web
│  │                      │  GlobalExceptionHand.java --- 全局异常处理
│  │                      │
│  │                      └─exception
│  │                              LoginException.java
│  │
│  └─resources
│      │  application.yml
│      │
│      ├─config
│      │      application-dev.yml
│      │      application-prod.yml
│      │      application-test.yml
│      │
│      ├─mappers
│      │      PermissionMapper.xml
│      │      RoleMapper.xml
│      │      RolePermissionMapper.xml
│      │      UserMapper.xml
│      │      UserRoleMapper.xml
│      │
│      ├─sql
│      │      shiro-V1.0.0.sql
│      │      shiro-V1.0.1.sql --- 最新版SQL
│      │
│      ├─static
│      └─templates
└─test
    └─java
        └─com
            └─leigq
                └─www
                    └─shiro
                        ├─base
                        │      BaseApplicationTests.java
                        │
                        └─test
                                ShiroApplicationTests.java
```
## 详细搭建过程
建议直接将代码拉下来对照着文档看

1、将最新版SQL导入数据库，SQL我已经放入项目中

2、引入依赖。
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.6.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.leigq.www</groupId>
    <artifactId>spring-boot-shiro</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>spring-boot-shiro</name>
    <description>shiro demo project for Spring Boot</description>

    <properties>
        <java.version>1.8</java.version>
        <druid.version>1.0.29</druid.version>
        <commons-collections4.version>4.1</commons-collections4.version>
        <mybatis-plus.version>3.1.1</mybatis-plus.version>
        <shiro-spring.version>1.4.0</shiro-spring.version>
        <shiro-redis.version>3.1.0</shiro-redis.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- druid数据库连接池 -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>${druid.version}</version>
        </dependency>

        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>${mybatis-plus.version}</version>
        </dependency>

        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-generator</artifactId>
            <version>${mybatis-plus.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-freemarker</artifactId>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <!--编写更少量的代码：使用apache commons工具类库:
        https://www.cnblogs.com/ITtangtang/p/3966955.html-->
        <!--apache.commons.lang3-->
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
        </dependency>

        <!--你可以把这个工具看成是java.util的扩展-->
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-collections4</artifactId>
            <version>${commons-collections4.version}</version>
        </dependency>

        <!--apache.codec:编码方法的工具类包
        https://blog.csdn.net/u012881904/article/details/52767853-->
        <dependency>
            <groupId>commons-codec</groupId>
            <artifactId>commons-codec</artifactId>
        </dependency>

        <dependency>
            <groupId>org.apache.shiro</groupId>
            <artifactId>shiro-spring</artifactId>
            <version>${shiro-spring.version}</version>
        </dependency>

        <dependency>
            <groupId>org.crazycake</groupId>
            <artifactId>shiro-redis</artifactId>
            <version>${shiro-redis.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
```
3、编辑application.yml

我项目中使用了多环境配置，你们可根据自己情况修改

```yml
mybatis-plus:
    configuration:
        map-underscore-to-camel-case: true
        use-generated-keys: true
    mapper-locations: classpath*:/mappers/**/*.xml
    type-aliases-package: com.leigq.www.shiro.domain.entity
server:
    tomcat:
        uri-encoding: UTF-8
spring:
    datasource:
        connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000;config.decrypt=true;config.decrypt.key=MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKPYsCl3alwZlRb1vKoFdVu0LP3Nm/+vH5iOWxI83pkUbrQc13Lxz/VT3D+H+ziaUpUsA+ZjG4iZGTDJWZnP8kcCAwEAAQ==
        driver-class-name: com.mysql.cj.jdbc.Driver
        filters: config,stat,wall,slf4j
        initialSize: 5
        maxActive: 20
        maxPoolPreparedStatementPerConnectionSize: 20
        maxWait: 60000
        minEvictableIdleTimeMillis: 300000
        minIdle: 5
        password: kGJF6c+pzVsf49LGs01ss0yijBGXIpNEp20cMkNCQo3ONaeMNPeoW9M89v+nGeiWs95/D2Ms59uGyydDGUWpmg==
        poolPreparedStatements: true
        testOnBorrow: false
        testOnReturn: false
        testWhileIdle: true
        timeBetweenEvictionRunsMillis: 60000
        type: com.alibaba.druid.pool.DruidDataSource
        url: jdbc:mysql://localhost:3306/shiro?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=true&serverTimezone=UTC
        username: root
        validationQuery: SELECT 1 FROM DUAL
    thymeleaf:
      cache: false
    redis:
      host: localhost
      port: 6379
      timeout: 2000s
      password: 111111
```
4、创建MySessionManager

```java
package com.leigq.www.shiro.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * 自定义session管理
 * <br/>
 * 传统结构项目中，shiro从cookie中读取sessionId以此来维持会话，在前后端分离的项目中（也可在移动APP项目使用），
 * 我们选择在ajax的请求头中传递sessionId，因此需要重写shiro获取sessionId的方式。
 * 自定义MySessionManager类继承DefaultWebSessionManager类，重写getSessionId方法
 * @author ：leigq
 * @date ：2019/7/1 10:52
 */
public class MySessionManager extends DefaultWebSessionManager {

    private static final String AUTHORIZATION = "Authorization";

    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String id = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        //如果请求头中有 Authorization 则其值为sessionId
        if (!StringUtils.isEmpty(id)) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return id;
        } else {
            //否则按默认规则从cookie取sessionId
            return super.getSessionId(request, response);
        }
    }
}

```

5、创建MyShiroRealm
```java
package com.leigq.www.shiro.config;

import com.leigq.www.shiro.domain.entity.Permission;
import com.leigq.www.shiro.domain.entity.Role;
import com.leigq.www.shiro.domain.entity.User;
import com.leigq.www.shiro.service.IPermissionService;
import com.leigq.www.shiro.service.IRoleService;
import com.leigq.www.shiro.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author ：leigq
 * @date ：2019/6/28 16:31
 * @description：自定义 shiroRealm, 主要是重写其认证、授权
 */
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private IUserService iUserService;

    @Resource
    private IRoleService iRoleService;

    @Resource
    private IPermissionService iPermissionService;


    /**
     * create by: leigq
     * description: 授权
     * create time: 2019/7/1 10:32
     *
     * @return 权限信息，包括角色以及权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.warn("开始执行授权操作.......");

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //如果身份认证的时候没有传入User对象，这里只能取到userName
        //也就是SimpleAuthenticationInfo构造的时候第一个参数传递需要User对象
        User user = (User) principals.getPrimaryPrincipal();

        // 查询用户角色，一个用户可能有多个角色
        List<Role> roles = iRoleService.getUserRoles(user.getUserId());

        for (Role role : roles) {
            authorizationInfo.addRole(role.getRole());
            // 根据角色查询权限
            List<Permission> permissions = iPermissionService.getRolePermissions(role.getRoleId());
            for (Permission p : permissions) {
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return authorizationInfo;
    }

    /**
     * create by: leigq
     * description: 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。
     * create time: 2019/7/1 09:04
     *
     * @return 身份验证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.warn("开始进行身份认证......");

        //获取用户的输入的账号.
        String userName = (String) token.getPrincipal();

        //通过username从数据库中查找 User对象.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User user = iUserService.findByUsername(userName);
        if (Objects.isNull(user)) {
            return null;
        }

        return new SimpleAuthenticationInfo(
                // 这里传入的是user对象，比对的是用户名，直接传入用户名也没错，但是在授权部分就需要自己重新从数据库里取权限
                user,
                // 密码
                user.getPassword(),
                // salt = username + salt
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                // realm name
                getName()
        );
    }

}

```
6、创建ShiroConfig
```java
package com.leigq.www.shiro.config;

import lombok.Data;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ：leigq
 * @date ：2019/6/28 16:53
 * @description：shiro配置
 */
@Configuration
@ConfigurationProperties(
        prefix = "spring.redis"
)
@Data
public class ShiroConfig {

    private String host = "localhost";
    private int port = 6379;
    private String password;
    private Duration timeout;

    /**
     * Filter工厂，设置对应的过滤条件和跳转条件
     * create by: leigq
     * create time: 2019/7/3 14:29
     *
     * @return ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 过滤器链定义映射
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        /*
         * anon:所有url都都可以匿名访问，authc:所有url都必须认证通过才可以访问;
         * 过滤链定义，从上向下顺序执行，authc 应放在 anon 下面
         * */
        filterChainDefinitionMap.put("/login", "anon");
        // 配置不会被拦截的链接 顺序判断，因为前端模板采用了thymeleaf，这里不能直接使用 ("/static/**", "anon")来配置匿名访问，必须配置到每个静态目录
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/html/**", "anon");
        // 所有url都必须认证通过才可以访问
        filterChainDefinitionMap.put("/**", "authc");

        // 配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了, 位置放在 anon、authc下面
        filterChainDefinitionMap.put("/logout", "logout");

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        // 配器shirot认登录累面地址，前后端分离中登录累面跳转应由前端路由控制，后台仅返回json数据, 对应LoginController中unauth请求
        shiroFilterFactoryBean.setLoginUrl("/un_auth");

        // 登录成功后要跳转的链接, 此项目是前后端分离，故此行注释掉，登录成功之后返回用户基本信息及token给前端
        // shiroFilterFactoryBean.setSuccessUrl("/index");

        // 未授权界面, 对应LoginController中 unauthorized 请求
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


    /**
     * 凭证匹配器（由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了）
     * create by: leigq
     * create time: 2019/7/3 14:30
     *
     * @return HashedCredentialsMatcher
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        // 散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    /**
     * 将自己的验证方式加入容器
     * create by: leigq
     * create time: 2019/7/3 14:30
     *
     * @return MyShiroRealm
     */
    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis, 使用的是shiro-redis开源插件
     * create by: leigq
     * create time: 2019/7/3 14:30
     *
     * @return RedisSessionDAO
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        redisSessionDAO.setSessionIdGenerator(sessionIdGenerator());
        redisSessionDAO.setExpire(1800);
        return redisSessionDAO;
    }

    /**
     * Session ID 生成器
     * <br/>
     * create by: leigq
     * <br/>
     * create time: 2019/7/3 16:08
     *
     * @return JavaUuidSessionIdGenerator
     */
    @Bean
    public JavaUuidSessionIdGenerator sessionIdGenerator() {
        return new JavaUuidSessionIdGenerator();
    }

    /**
     * 自定义sessionManager
     * create by: leigq
     * create time: 2019/7/3 14:31
     *
     * @return SessionManager
     */
    @Bean
    public SessionManager sessionManager() {
        MySessionManager mySessionManager = new MySessionManager();
        mySessionManager.setSessionDAO(redisSessionDAO());
        return mySessionManager;
    }

    /**
     * 配置shiro redisManager, 使用的是shiro-redis开源插件
     * <br/>
     * create by: leigq
     * <br/>
     * create time: 2019/7/3 14:33
     *
     * @return RedisManager
     */
    private RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        redisManager.setTimeout((int) timeout.toMillis());
        redisManager.setPassword(password);
        return redisManager;
    }

    /**
     * cacheManager 缓存 redis实现, 使用的是shiro-redis开源插件
     * <br/>
     * create by: leigq
     * <br/>
     * create time: 2019/7/3 14:33
     *
     * @return RedisCacheManager
     */
    @Bean
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        // 必须要设置主键名称，shiro-redis 插件用过这个缓存用户信息
        redisCacheManager.setPrincipalIdFieldName("userId");
        return redisCacheManager;
    }

    /**
     * create by: leigq
     * description: 权限管理，配置主要是Realm的管理认证
     * create time: 2019/7/1 10:09
     *
     * @return SecurityManager
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }

    /*
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public SimpleCookie cookie() {
        // cookie的name,对应的默认是 JSESSIONID
        SimpleCookie cookie = new SimpleCookie("SHARE_JSESSIONID");
        cookie.setHttpOnly(true);
        //  path为 / 用于多个系统共享 JSESSIONID
        cookie.setPath("/");
        return cookie;
    }

    /* 此项目使用 shiro 场景为前后端分离项目，这里先注释掉，统一异常处理已在 GlobalExceptionHand.java 中实现 */
    /**
     * create by: leigq
     * description: 异常处理, 详见：https://www.cnblogs.com/libra0920/p/6289848.html
     * create time: 2019/7/1 10:28
     * @return SimpleMappingExceptionResolver
     */
//    @Bean(name = "simpleMappingExceptionResolver")
//    public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
//        SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
//        Properties mappings = new Properties();
//        mappings.setProperty("DatabaseException", "databaseError");//数据库异常处理
//        mappings.setProperty("UnauthorizedException", "/user/403");
//        r.setExceptionMappings(mappings);  // None by default
//        r.setDefaultErrorView("error");    // No default
//        r.setExceptionAttribute("exception");     // Default is "exception"
//        //r.setWarnLogCategory("example.MvcLogger");     // No default
//        return r;
//    }
}
```
7、创建LoginController
```java
package com.leigq.www.shiro.controller;

import com.leigq.www.shiro.bean.CacheUser;
import com.leigq.www.shiro.bean.Response;
import com.leigq.www.shiro.domain.entity.User;
import com.leigq.www.shiro.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ：leigq
 * @date ：2019/6/28 16:55
 * @description：登录Controller
 */
@Slf4j
@RestController
public class LoginController {

    @Resource
    private IUserService iUserService;

    @Resource
    private Response response;

    /**
     * create by: leigq
     * description: 登录
     * create time: 2019/6/28 17:11
     *
     * @return 登录结果
     */
    @PostMapping("/login")
    public Response login(User user) {
        log.warn("进入登录.....");

        String userName = user.getUserName();
        String password = user.getPassword();

        if (StringUtils.isBlank(userName)) {
            return response.failure("用户名为空！");
        }

        if (StringUtils.isBlank(password)) {
            return response.failure("密码为空！");
        }

        CacheUser loginUser = iUserService.login(userName, password);
        // 登录成功返回用户信息
        return response.success("登录成功！", loginUser);
    }

    /**
     * create by: leigq
     * description: 登出
     * create time: 2019/6/28 17:37
     */
    @GetMapping("/logout")
    public Response logOut() {
        iUserService.logout();
        return response.success("登出成功！");
    }

    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     * <br/>
     * create by: leigq
     * <br/>
     * create time: 2019/7/3 14:53
     * @return  
     */
    @RequestMapping("/un_auth")
    public Response unAuth() {
        return response.failure(HttpStatus.UNAUTHORIZED, "用户未登录！", null);
    }

    /**
     * 未授权，无权限，此处返回未授权状态信息由前端控制跳转页面
     * <br/>
     * create by: leigq
     * <br/>
     * create time: 2019/7/3 14:53
     * @return
     */
    @RequestMapping("/unauthorized")
    public Response unauthorized() {
        return response.failure(HttpStatus.FORBIDDEN, "用户无权限！", null);
    }
}

```
8、具体登录方法
```java
package com.leigq.www.shiro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leigq.www.shiro.bean.CacheUser;
import com.leigq.www.shiro.domain.entity.User;
import com.leigq.www.shiro.domain.mapper.UserMapper;
import com.leigq.www.shiro.service.IUserService;
import com.leigq.www.shiro.web.exception.LoginException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author leigq
 * @since 2019-06-28
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User findByUsername(String username) {
        return baseMapper.selectOne(
                new LambdaQueryWrapper<User>().eq(User::getUserName, username)
        );
    }

    @Override
    public CacheUser login(String userName, String password) {

        // 获取Subject实例对象，用户实例
        Subject currentUser = SecurityUtils.getSubject();

        // 将用户名和密码封装到UsernamePasswordToken
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);

        CacheUser cacheUser;

        // 4、认证
        try {
            // 传到 MyShiroRealm 类中的方法进行认证
            currentUser.login(token);
            // 构建缓存用户信息返回给前端
            User user = (User) currentUser.getPrincipals().getPrimaryPrincipal();
            cacheUser = CacheUser.builder()
                    .token(currentUser.getSession().getId().toString())
                    .build();
            BeanUtils.copyProperties(user, cacheUser);
            log.warn("CacheUser is {}", cacheUser.toString());
        } catch (UnknownAccountException e) {
            log.error("账户不存在异常：", e);
            throw new LoginException("账号不存在!", e);
        } catch (IncorrectCredentialsException e) {
            log.error("凭据错误（密码错误）异常：", e);
            throw new LoginException("密码不正确!", e);
        } catch (AuthenticationException e) {
            log.error("身份验证异常:", e);
            throw new LoginException("用户验证失败!", e);
        }
        return cacheUser;
    }

    @Override
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

    @Override
    public List<User> listUsers() {
        return baseMapper.selectList(new LambdaQueryWrapper<>());
    }
}
```
上面我列出了项目中主要的几个类，大家可以对照着项目看，每个类中的注释已经写的很详细了。

## 使用及测试

我们配置每个接口的权限使用`@RequiresPermissions("user:view")`注解即可，其中`user:view`对应权限表中的权限。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190706160507657.png)

1、登录测试

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190706160656648.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM0ODQ1Mzk0,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190706160825460.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM0ODQ1Mzk0,size_16,color_FFFFFF,t_70)

登录成功会将用户信息存入缓存。

2、请求查询用户接口

我们先输入错误的token试试

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190706160924408.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM0ODQ1Mzk0,size_16,color_FFFFFF,t_70)

我们再输入正确的token试试
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190706160956360.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM0ODQ1Mzk0,size_16,color_FFFFFF,t_70)

3、请求用户删除接口

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190706161155901.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM0ODQ1Mzk0,size_16,color_FFFFFF,t_70)

因为我们没有给此用户配置此权限，所以返回无权限

4、退出登录

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190706161535906.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM0ODQ1Mzk0,size_16,color_FFFFFF,t_70)

我们再请求用户列表接口

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190706161618536.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM0ODQ1Mzk0,size_16,color_FFFFFF,t_70)

## 感谢

- [springboot整合shiro应用:thumbsup:](https://www.cnblogs.com/ll409546297/p/7815409.html)

- [Springboot2.0 集成shiro权限管理:gift:](https://www.cnblogs.com/asker009/p/9471712.html)

- [SpringBoot+Shiro+Redis共享Session入门小栗子:hearts:](https://www.cnblogs.com/LUA123/p/9337963.html)
