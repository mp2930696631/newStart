package com.hz.consumer02;

import com.hz.consumer02.grayscale.GrayscaleRule;
import com.hz.consumer02.grayscale.interceptor.GrayscaleInterceptor;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ServletComponentScan
public class Consumer02Application {

    public static void main(String[] args) {
        SpringApplication.run(Consumer02Application.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new GrayscaleInterceptor());
        return restTemplate;
    }

    // 因为引入了ribbon-discovery-filter-spring-cloud-starter，所以需要加上@Primary注解
    // 因为这个包自定义了一个filter：MetadataAwareRule
    // 所以，这个包的本质就是自定义IRule，只不过只能是轮询了，
    @Bean
    @Primary
    public IRule getRule() {
        return new GrayscaleRule();
    }
}
