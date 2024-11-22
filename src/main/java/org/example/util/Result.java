package org.example.util;

import lombok.Data;

@Data
public class Result {
    // 返回码
    private Integer code;
    // 返回信息
    private String massage;
    // 返回数据
    private Object data;

    // 构造函数
    public Result(Integer code, String massage, Object data) {
        this.code = code;
        this.massage = massage;
        this.data = data;
    }

    public Result(ResultCodeEnum resultCodeEnum, Object data) {
        this(resultCodeEnum.getCode(), resultCodeEnum.getMassage(), data);
    }

    public Result(ResultCodeEnum resultCodeEnum) {
        this(resultCodeEnum, null);
    }

    // 成功返回
    public static Result ok(Object data) {
        return new Result(ResultCodeEnum.SUCCESS, data);
    }

    public static Result ok() {
        return ok(null);
    }

    // 失败返回
    public static Result failure(Object data) {
        return new Result(ResultCodeEnum.FAILURE, data);
    }

    public static Result failure() {
        return failure(null);
    }
}
