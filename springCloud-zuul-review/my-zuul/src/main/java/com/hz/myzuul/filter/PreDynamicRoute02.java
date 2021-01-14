package com.hz.myzuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 将url中查询参数tag为dynamic的请求，转发到http://provider-dynamic/prov/dynamic/port,
 * 否则，不做处理
 *
 * 最好也是后于PreDecorationFilter执行，这样不会破坏原来的逻辑
 *
 * @author zehua
 * @date 2021/1/14 13:24
 */
// @Component
public class PreDynamicRoute02 extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 6;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        final RequestContext requestContext = RequestContext.getCurrentContext();
        final HttpServletRequest request = requestContext.getRequest();
        final String pathTag = request.getParameter("tag");
        if (pathTag.equals("dynamic")) {
            try {
                requestContext.setRouteHost(new URL("http://localhost:5001/prov/dynamic/port"));
                // 因为这个filter的优先级为6，所以后于PreDecorationFilter执行，
                // 而SimpleHostRoutingFilter最后拼接出来的url是setRouteHost中的url+REQUEST_URI_KEY的值，
                // 所以，我们可以将REQUEST_URI_KEY设置为空
                requestContext.set(FilterConstants.REQUEST_URI_KEY, "");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
