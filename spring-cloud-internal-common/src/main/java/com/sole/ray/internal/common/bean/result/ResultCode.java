package com.sole.ray.internal.common.bean.result;

public enum ResultCode {

    /**
     * 成功
     */
    SUCCESS("0", "success"),

    /**
     * 内部错误
     */
    INTERNAL_ERROR("0x10001", "internal error"),

    /**
     * 用户名错误或不存在
     */
    USERNAME_ERROR("0x10002", "username error or does not exist"),

    /**
     * 密码错误
     */
    PASSWORD_ERROR("0x10003", "password error"),

    /**
     * 用户名不能为空
     */
    USERNAME_EMPTY("0x10004", "username can not be empty"),

    /**
     * 签名错误
     */
    SIGN_ERROR("0x10005", "sign is not the same"),

    /**
     * 熔断异常
     */
    HYSTRIX_ERROR("0x20001", "服务不可用，触发熔断！！");

    /**
     * 结果码
     */
    private String code;

    /**
     * 结果码描述
     */
    private String msg;


    private ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}