package com.example.demo.domain;

/**
 * 统一封装API返回信息
 * 引用网络代码
 */
public class RestResult {
    // 状态码
    private int code;
    // 消息
    private String message;
    // 额外的内容
    private Object data;

    public RestResult() {
    }

    public RestResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public RestResult setCode(ResultCode code) {
        this.code = code.getCode();
        return this;
    }

    public int getCode() {
        return code;
    }

    public RestResult setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public RestResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public RestResult setData(Object data) {
        this.data = data;
        return this;
    }
}
