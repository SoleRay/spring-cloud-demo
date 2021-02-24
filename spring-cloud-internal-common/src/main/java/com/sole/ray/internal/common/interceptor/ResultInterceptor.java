package com.sole.ray.internal.common.interceptor;

import com.sole.ray.internal.common.anno.ResponseResult;
import com.sole.ray.internal.common.util.Constants;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class ResultInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Class<?> controllerClass = handlerMethod.getBeanType();
            Method method = handlerMethod.getMethod();
            if(controllerClass.isAnnotationPresent(ResponseResult.class)){
                request.setAttribute(Constants.RESPONSE_RESULT_ANN,controllerClass.getAnnotation(ResponseResult.class));
            }else if(method.isAnnotationPresent(ResponseResult.class)){
                request.setAttribute(Constants.RESPONSE_RESULT_ANN,method.getAnnotation(ResponseResult.class));
            }
        }
        return true;
    }
}