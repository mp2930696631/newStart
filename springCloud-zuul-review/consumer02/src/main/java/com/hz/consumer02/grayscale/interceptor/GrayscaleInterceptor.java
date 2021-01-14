package com.hz.consumer02.grayscale.interceptor;

import com.hz.consumer02.entity.GrayscalePublishing;
import com.hz.consumer02.grayscale.RestTemplateRequestHolder;
import com.hz.consumer02.service.GrayscaleService;
import io.jmnarloch.spring.cloud.ribbon.api.RibbonFilterContext;
import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;

/**
 * @author zehua
 * @date 2021/1/14 17:53
 */
public class GrayscaleInterceptor implements ClientHttpRequestInterceptor {
    /*@Autowired
    private GrayscaleService grayscaleService;*/

    // 使用ribbon-discovery-filter-spring-cloud-starter
    // 注意，因为这个需要用到GrayscaleService，所以还得加上@Component
    /*@Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        // 先移除
        final RibbonFilterContext currentContext = RibbonFilterContextHolder.getCurrentContext();
        currentContext.remove("version");
        final URI uri = request.getURI();
        final String pathUserId = uri.getQuery().split("=")[1];
        String pathServiceId = uri.getHost();
        final GrayscalePublishing grayscale = grayscaleService.getGrayscaleByUserIdAndServiceId(pathUserId, pathServiceId);
        if (grayscale != null) {
            currentContext.add("version", grayscale.getVersion().toString());
        }
        return execution.execute(request, body);
    }*/

    // 自定义IRule使用这个方法
    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        RestTemplateRequestHolder.set(httpRequest);
        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }


}
