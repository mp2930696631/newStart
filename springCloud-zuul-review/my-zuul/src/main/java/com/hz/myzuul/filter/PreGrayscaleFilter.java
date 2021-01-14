package com.hz.myzuul.filter;

import com.hz.myzuul.entity.GrayscalePublishing;
import com.hz.myzuul.service.GrayscaleService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jmnarloch.spring.cloud.ribbon.api.RibbonFilterContext;
import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zehua
 * @date 2021/1/14 9:28
 */
@Component
public class PreGrayscaleFilter extends ZuulFilter {
    @Autowired
    private GrayscaleService grayscaleService;

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

    @Override
    public Object run() throws ZuulException {
        final RibbonFilterContext ribbonFilterContext = RibbonFilterContextHolder.getCurrentContext();
        // 因为使用了threadLocal+tomcat线程池的原因，所以需要先remove
        ribbonFilterContext.remove("version");
        final RequestContext requestContext = RequestContext.getCurrentContext();
        final HttpServletRequest request = requestContext.getRequest();
        final String pathUserId = request.getParameter("userId");
        final List<GrayscalePublishing> grayscaleByUserId = grayscaleService.getGrayscaleByUserId(pathUserId);
        final String requestURI = request.getRequestURI();
        String pathServiceId = requestURI.substring(1).split("/")[0];
        if (grayscaleByUserId == null) {
            return null;
        }
        for (GrayscalePublishing grayscalePublishing : grayscaleByUserId) {
            final String serviceId = grayscalePublishing.getServiceId();
            final String userId = grayscalePublishing.getUserId();
            final Integer version = grayscalePublishing.getVersion();
            if (userId.equals(pathUserId) && serviceId.equals(pathServiceId)) {
                RibbonFilterContextHolder.getCurrentContext().add("version", version.toString());
            }
        }
        return null;
    }
}
