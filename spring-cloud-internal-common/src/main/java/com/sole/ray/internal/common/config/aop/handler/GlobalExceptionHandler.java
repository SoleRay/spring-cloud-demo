package com.sole.ray.internal.common.config.aop.handler;

import com.sole.ray.internal.common.bean.result.ErrorResult;
import com.sole.ray.internal.common.bean.result.ResultCode;
import com.sole.ray.internal.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理所有不可知异常
     *
     * @param e 异常
     * @return json结果
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorResult handleException(Exception e) {
        // 打印异常堆栈信息
        log.error(e.getMessage(), e);
        return ErrorResult.failure(ResultCode.INTERNAL_ERROR);
    }

    /**
     * 处理所有业务异常
     *
     * @param e 业务异常
     * @return json结果
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ErrorResult handleBusinessException(BusinessException e) {
        // 不打印异常堆栈信息
        log.error(e.getMsg());
        return ErrorResult.failure(e.getResultCode());
    }
}
