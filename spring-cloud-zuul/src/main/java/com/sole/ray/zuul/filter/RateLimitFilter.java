package com.sole.ray.zuul.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.constants.ZuulConstants;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 限流过滤器
 */
@Component
public class RateLimitFilter extends ZuulFilter {

    //2 = 每秒2个，0.1 = 10秒1个
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(2);

    @Override
    public boolean shouldFilter() {
        //后面所有的filter都加这个判断
        RequestContext context = RequestContext.getCurrentContext();
        Boolean limit = (Boolean) context.get("limit");
        return limit == null ? false : limit;
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
        if (RATE_LIMITER.tryAcquire()) {
            return null;
        } else {
            RequestContext context = RequestContext.getCurrentContext();
            context.set("limit", false);
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());
        }
        return null;
    }


}
