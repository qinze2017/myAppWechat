package com.ze.market.enums;

import lombok.Getter;

@Getter
public enum ProductStatusEnum {
    ON(0, "On shelf"),
    OFF(1,"Off shelf")
    ;

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
