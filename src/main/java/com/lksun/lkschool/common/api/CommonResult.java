package com.lksun.lkschool.common.api;

import lombok.Data;

@Data
public class CommonResult<T> {
    private long code = 200;
    private String message = "操作成功";
    private T data;

    protected CommonResult() {
    }

    public CommonResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public static <T> CommonResult<T> actionFailed(String message) {
        return new CommonResult<T>(400, message, null);
    }

    public static <T> CommonResult<T> unauthorized(String message) {
        return new CommonResult<T>(401, message, null);
    }

    public static <T> CommonResult<T> validateFailed(String message) {
        return new CommonResult<T>(402, message, null);
    }

    public static <T> CommonResult<T> forbidden(String message) {
        return new CommonResult<T>(403, message, null);
    }

    public static <T> CommonResult<T> notFound(String message) {
        return new CommonResult<T>(404, message, null);
    }

    public static <T> CommonResult<T> error(long code,String message) {
        return new CommonResult<T>(code, message, null);
    }


    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(200, "操作成功", data);
    }
}
