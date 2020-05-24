package com.ze.market.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {
    New(0, "new order"),
    FINISHED(1, "finished"),
    CANCEL(2, "canceld"),
    ;

    private Integer code;

    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
