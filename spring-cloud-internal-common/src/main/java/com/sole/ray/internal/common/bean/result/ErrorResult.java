package com.sole.ray.internal.common.bean.result;

import lombok.Data;

@Data
public class ErrorResult<T> extends AbstractResult {

    /** 详细错误 */
    private T errors;

    protected ErrorResult(ResultCode resultCode) {
        this(resultCode,null);
    }

    protected ErrorResult(ResultCode resultCode,T errors) {
        super(resultCode);
    }


    public static ErrorResult failure(ResultCode resultCode){
        return new ErrorResult(resultCode);
    }

    public static ErrorResult failure(ResultCode resultCode,Object errors){
        return new ErrorResult(resultCode,errors);
    }

}
