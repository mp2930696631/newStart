package com.hz.microservicef02.factory;

import com.hz.microservicef02.service.ApiService;
import com.hz.userapi.entiry.Person;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author zehua
 * @date 2020/12/28 21:19
 */
@Component
public class WebErr implements FallbackFactory<ApiService> {
    @Override
    public ApiService create(Throwable throwable) {
        return new ApiService() {
            @Override
            public String getConf() {
                return "getConf error";
            }

            @Override
            public String getPort() {
                return "getPort error";
            }

            @Override
            public String testRibbon() {
                return "testRibbon error";
            }

            @Override
            public String testHystrix() {
                return "testHystrix error";
            }

            @Override
            public Person getPerson() {
                return new Person("error", 0);
            }

            @Override
            public String testPost(Map<String, Person> map) {
                return "testPost error";
            }
        };
    }
}
