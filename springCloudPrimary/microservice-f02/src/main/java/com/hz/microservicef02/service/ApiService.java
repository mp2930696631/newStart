package com.hz.microservicef02.service;

import com.hz.microservicef02.factory.WebErr;
import com.hz.userapi.UserApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zehua
 * @date 2020/12/28 21:18
 */
@FeignClient(value = "microservice-n", fallbackFactory = WebErr.class)
public interface ApiService extends UserApi {

    @RequestMapping("/prov/conf")
    String getConf();

}
