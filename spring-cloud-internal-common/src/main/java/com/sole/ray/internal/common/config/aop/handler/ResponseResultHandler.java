package com.sole.ray.internal.common.config.aop.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sole.ray.internal.common.anno.ResponseResult;
import com.sole.ray.internal.common.bean.result.ErrorResult;
import com.sole.ray.internal.common.bean.result.Result;
import com.sole.ray.internal.common.bean.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;


/**
 * 如果Controller的类或者方法上@ResponseResult
 * 那么对返回结果进行封装
 *
 * 注意：如果方法的返回值是String，需要特殊处理
 * 因为当方法返回值是String时，用的convert是StringHttpMessageConverter
 * 而返回值是Object时，用的
 */
@Slf4j
@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {

        //当前request请求的method
        Method requestMethod = methodParameter.getMethod();

        //当前request请求的method所在的Controll的class
        Class<?> requestControllerClass = requestMethod.getDeclaringClass();

        if (requestControllerClass.isAnnotationPresent(ResponseResult.class) || requestMethod.isAnnotationPresent(ResponseResult.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        if(body instanceof Result){
            return body;
        }

        if (body instanceof ErrorResult) {
            ErrorResult errorResult = (ErrorResult) body;
            return Result.failure(errorResult.getCode(), errorResult.getMessage(), errorResult.getErrors());
        }

        if (body instanceof String){
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(Result.success(body));
            } catch (JsonProcessingException e) {
                log.error(e.getMessage(),e);
                return Result.failure(ResultCode.INTERNAL_ERROR);
            }
        }
        return Result.success(body);
    }
}
