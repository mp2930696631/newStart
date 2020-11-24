package com.hz;

import com.hz.servlet.listener.MyListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@ServletComponentScan
@ImportResource("classpath:spring-mybatis.xml")
public class SpringbootReviewApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootReviewApplication.class, args);
    }

    // 文件上传相关的设置
    @Bean
    public MultipartConfigElement configElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // factory.setMaxFileSize(DataSize.ofBytes(10240L));
        factory.setMaxRequestSize(DataSize.ofBytes(10240L));

        return factory.createMultipartConfig();
    }

    @Bean
    public MyListener getMyListener() {
        return new MyListener();
    }

}
