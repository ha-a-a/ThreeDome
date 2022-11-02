package com.example.demo.common;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author kaenry
 * @date 2016/9/20
 * RestResult
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResult<T> {

    private int code;

    private String message;

    private T result;

    private RestResult() {}

    public static <T> RestResult<T> newInstance() {
        return new RestResult<>();
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
