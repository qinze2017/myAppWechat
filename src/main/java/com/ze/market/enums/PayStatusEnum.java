package com.ze.market.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum {

    WAIT(0, "wait for payment"),
    SUCCESS(1, "payment success"),
    ;

    private Integer code;

    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
