package com.sole.ray.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 作用：对ip进行限制
 */
//@Component
public class HostFilter extends ZuulFilter {

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("HostFilter");
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        String host = getHost(request);

        //设置成了false，只是控制不向route过滤器传递，但pre的过滤器还是会走的
        currentContext.setSendZuulResponse(false);
        return null;
    }

    private String getHost(HttpServletRequest request) {
        String host = request.getHeader("X-Forwarded-For");
        return host;
    }


}
