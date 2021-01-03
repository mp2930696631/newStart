package com.hz.springbootActivemq;

import com.hz.springbootActivemq.queue.Sender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringbootActivemqApplication {


    public static void main(String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(SpringbootActivemqApplication.class, args);
        final Sender sender = context.getBean(Sender.class);
        sender.send02();
    }

}
