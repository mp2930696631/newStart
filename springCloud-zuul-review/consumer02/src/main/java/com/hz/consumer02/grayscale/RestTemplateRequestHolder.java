package com.hz.consumer02.grayscale;

import org.springframework.http.HttpRequest;

/**
 * @author zehua
 * @date 2021/1/14 18:03
 */
public class RestTemplateRequestHolder {
    private static ThreadLocal<HttpRequest> httpRequestThreadLocal = new ThreadLocal<>();

    public static void set(HttpRequest httpRequest) {
        httpRequestThreadLocal.set(httpRequest);
    }

    public static HttpRequest get() {
        return httpRequestThreadLocal.get();
    }

    public static void remove() {
        httpRequestThreadLocal.remove();
    }
}
