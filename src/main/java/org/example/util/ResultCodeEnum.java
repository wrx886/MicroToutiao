package org.example.util;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {
    SUCCESS(200, "success"),
    FAILURE(500, "failure"),
    USERNAME_OR_PASSWORD_ERROR(501, "username or password error!"),
    NOT_LOGIN(504, "notLogin"),
    USERNAME_USED(505, "userNameUsed");

    // 返回代码
    private final Integer code;
    // 返回信息
    private final String massage;

    ResultCodeEnum(Integer code, String massage) {
        this.code = code;
        this.massage = massage;
    }
}
