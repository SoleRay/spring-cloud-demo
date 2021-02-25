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
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;


/**
 * 如果Controller的类或者方法上@ResponseResult
 * 那么对返回结果进行封装
 *
 * 注意事项：
 *     1.方法的返回类型尽量不要为Object
 *          在ResponseBodyAdvice#beforeBodyWrite被调用之前会有很多预判处理
 *       其中，影响比较大的是MediaType的预判处理。
 *       其中有一种情况，会导致MediaType为空，而跳过处理。
 *       这种情况是：返回类型 = Object，且返回值 = null
 *       所以，尽量不要让方法是返回类型声明为Object,如果必须声明为Object,那么返回值必须进行null检查
 *
 *     2.方法的返回类型尽量不要为String
 *       当方法返回值是String时，优先用的convert是StringHttpMessageConverter
 *       而非String的返回值，只要Response的Head里的ContentType = application/json，用的就是MappingJackson2HttpMessageConverter
 *       关于SpringMvc Converter默认初始化的顺序，详情见{@link WebMvcConfigurationSupport}#addDefaultHttpMessageConverters
 *       所以，beforeBodyWrite方法对String类型的返回值做了特殊处理
 *
 *     3.方法可以为void，即没有返回值
 *       此时，依然会触发beforeBodyWrite，data=null
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
