package com.eoi.core.common;


import lombok.Getter;

/**
 * 状态码
 */
@Getter
public enum StateCode {

    CODE_200("200", "成功!"), CODE_500("500", "失败!");

    String code;
    String message;

    StateCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
