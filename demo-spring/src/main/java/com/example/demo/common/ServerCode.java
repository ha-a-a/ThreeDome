package com.example.demo.common;

/**
 *
 * @author kaenry
 * @date 2016/9/20
 * ErrorCode
 */
public enum ServerCode {

    /**
     * 公共code
     */
    ILLEGAL_PARAMS(401, "request params invalid"),
    SERVER_ERROR(500, "server is busy"),
    SUCCESS(200, "success"),
    FAIL(400, "fail");

    /**
     * 用户、角色、权限管理code
     */
//    USER_ALREADY_EXISTS(10001, ""),
//    USER_ALREADY_EXISTS(10001, "fail"),
//    USER_ALREADY_EXISTS(10001, "fail");

    ServerCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
