package com.sole.ray.internal.common.bean.result;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class AbstractResult {

    /** 结果码 */
    private String code;

    /** 结果详细信息 */
    private String message;

    public AbstractResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    protected AbstractResult(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMsg();
    }
}
