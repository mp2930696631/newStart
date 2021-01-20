## 这个项目主要包括
### 1、灰度发布
```
> 网关中的灰度发布
使用了ribbon-discovery-filter-spring-cloud-starter框架

> 服务中的灰度发布
通过自定义RestTemplate 拦截器，获取HttpRequest对象，保存在ThreadLocal中

使用了两种方式
1、自定义IRule（见consumer02\src\main\java\com\hz\consumer01\grayscale\GrayscaleRule.java）

2、使用ribbon-discovery-filter-spring-cloud-starter框架，（见consumer01\grayscale\interceptor\GrayscaleInterceptor.java）

关键是使用RestTemplate 拦截器，获取HttpRequest对象，保存在ThreadLocal中
```

### 2、限流
```
> 1、网关限流
使用的是guava的RateLimter

> 2、服务中的限流
使用了servlet的filter+guava的RateLimter
```

### 3、eureka的分区
```
consumer01和provider0\1\2\3同属于hongfu-one
consumer02和provider0\4\5\6同属于hongfu-two

需要注意的是：eureka.client.prefer-same-zone-eureka=true 这个配置项只是控制client从是否从同区域的server注册或者拉取！！而与consumer请求的provider无关  

注意一下两点
> 1、使consumer只请求与自己同一区域的provider：只需要配置consumer和provider的metadata就可以了！！

> 2、使eureka-client从与自己同区域的eureka-server拉取或注册，需要配置下面选项
eureka.client.region=hunan
eureka.client.availability-zones.hunan=hongfu-one,hongfu-two
eureka.client.service-url.hongfu-one=http://eur1.com:7001/eureka/
eureka.client.service-url.hongfu-two=http://eur2.com:7002/eureka/
eureka.instance.metadata-map.zone=hongfu-one
这样的话，这个client优先从eureka.client.service-url.hongfu-one注册或者拉取     
```