package com.hz.myzuul.filter.limit;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * 网关限流
 *
 * @author zehua
 * @date 2021/1/14 15:02
 */
// @Component
public class PreLimitFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return -10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    private static RateLimiter limiter = RateLimiter.create(2);

    @Override
    public Object run() throws ZuulException {
        if (limiter.tryAcquire()) {
            return null;
        }

        final RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setSendZuulResponse(false);
        ctx.setResponseBody("被限流啦 from zuul..............");
        ctx.getResponse().setCharacterEncoding("GB2312");
        return null;
    }
}
