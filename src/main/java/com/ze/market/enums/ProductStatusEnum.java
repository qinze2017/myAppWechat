package com.ze.market.enums;

import lombok.Getter;

@Getter
public enum ProductStatusEnum {
    ON(0, "On shelf"),
    OFF(1,"Off shelf")
    ;

    private Integer code;

    private String msg;

    ProductStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
