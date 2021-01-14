package com.hz.myzuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 将provider-dynamic/**，转到provider-dynamic/prov/port
 * <p>
 * 最好还是通过修改这个filter的优先级，使其后于PreDecorationFilter（优先级是5）执行，这样的话，就只需要设置这一个值FilterConstants.REQUEST_URI_KEY就可以了
 * 否则，需要如下面代码一样，设置三个值
 *
 * @author zehua
 * @date 2021/1/14 11:56
 */
//@Component
public class PreDynamicRoute01 extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        final RequestContext currentContext = RequestContext.getCurrentContext();
        final HttpServletRequest request = currentContext.getRequest();
        final String requestURI = request.getRequestURI();
        String pathServiceId = requestURI.substring(1).split("/")[0];
        if (pathServiceId.equals("provider-dynamic")) {
            currentContext.set(FilterConstants.SERVICE_ID_KEY, "provider-dynamic");
            currentContext.set(FilterConstants.REQUEST_URI_KEY, "/prov/port");
            currentContext.set(FilterConstants.RETRYABLE_KEY, false);
        }

        return null;
    }
}
