热部署虽然不好使，但是还是配置一下

使用trigger的方式来触发热部署

```
<dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-devtools</artifactId>
     <optional>true</optional>
</dependency>
```

## 打成war部署到外置tomcat的步骤
>1、修改pom文件，使其打成war包
```
<packing>war</packing>
```
>2、修改启动类，使其继承SpringBootServletInitializer,并重写其中一个方法
```
SpringBootServletInitializer
@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(启动类.class);
    }
```

##限制文件上传大小的两种方式
>1、@Bean
```
@Bean
    public MultipartConfigElement configElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // factory.setMaxFileSize(DataSize.ofBytes(10240L));
        factory.setMaxRequestSize(DataSize.ofBytes(10240L));

        return factory.createMultipartConfig();
    }
```
>2、配置文件
```
spring.servlet.multipart.max-request-size=3KB
```

##在测试session监听器的时候，需要注意使用requst.getSession()来创建session
- 如果不使用req.getSession是不会创建session的，如果调用该方法，会自动像cookie中写入JSESSIONID
  而如果请求jsp页面的话，因为jsp内嵌session,所有会有jsessionid
  
- 在使用监听器的时候，可以使用WebListener，也可以直接注册为bean

##springboot整合mybatis
>1、mybatis的starter版本是2.1.4，不支持最新版的springboot
>2、使用xml来进行配置,（就是依据ssm中的mybatis配置来的）步骤如下
- 导入pom依赖,(需要注意的的点就是：因为ssm中的mybatis依赖spring中的一些东西，如tx、spring-jdbc等，所有最后加入了spring-jdbc-starter)
```
<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.22</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/com.alibaba/druid -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.2.3</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-spring -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>2.0.6</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.6</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
```
- mybatis配置（新建一个spring的配置文件）
```
<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="username" value="${jdbc.username}"></property>
        <property name="password" value="${jdbc.password}"></property>
        <property name="url" value="${jdbc.url}"></property>
    </bean>

    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="mapperLocations" value="classpath:com/hz/mybatis/mapper/**/*.xml"></property>
        <property name="dataSource" ref="dataSource"></property>
    </bean>

    <bean id="mapperScan" class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.hz.mybatis.mapper"></property>
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"></property>
    </bean>
```
## 最后需要特别注意
>1、MapperScannerConfigurer的basePackage的配置，这个需要特别注意，
必须写到mapper层，因为mybatis会为其包下面的所有接口生成代理类，这个并不是我们想要的