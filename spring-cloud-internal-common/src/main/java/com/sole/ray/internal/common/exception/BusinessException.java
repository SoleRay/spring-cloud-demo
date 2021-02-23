package com.sole.ray.internal.common.exception;


import com.sole.ray.internal.common.bean.result.ResultCode;
import lombok.Data;

@Data
public class BusinessException extends RuntimeException {

    /** 结果码 */
    private String code;

    /** 结果码描述 */
    private String msg;

    /** 结果码枚举 */
    private ResultCode resultCode;

    public BusinessException(ResultCode resultCode) {
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.resultCode = resultCode;
    }

}
