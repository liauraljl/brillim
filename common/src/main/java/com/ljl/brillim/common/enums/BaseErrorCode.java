package com.ljl.brillim.common.enums;

/**
 * 通用BaseErrorCode定义
 *
 */
public enum BaseErrorCode {
    /*****************
     ***Basic Error***
     ****************/
    SUCCESS(0, "success"),
    FAILED(2900000000001L, "System Error");

    private long code;
    private String msg;

    BaseErrorCode(long code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public long getCode() {
        return code;
    }

    public enum FIV_API_FAILD {}

}
