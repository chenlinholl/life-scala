package com.eoi.slick.common;

import lombok.Getter;

@Getter
public enum StateCode {

    CODE_200("200", "成功");

    String code;
    String message;

    StateCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
