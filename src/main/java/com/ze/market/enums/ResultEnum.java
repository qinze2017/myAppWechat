package com.ze.market.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    PRODUCT_NOT_EXIST(10, "product not exist"),
    PRODUCT_STOCK_ERROR(11, "stock not correct"),
    ORDER_NOT_EXIST(12, "order not exist"),
    ORDERDETAIL_NOT_EXIST(13, "order detail not exist"),
    ORDER_STATUS_ERROR(14, "order status is not correct"),
    ORDER_UPDATE_FAIL(15, "order update failed"),
    ORDER_DETAIL_EMPTY(16, "order detail is empty"),
    ORDER_PAY_STATUS_ERROR(17, "order pay status has error"),
    PARAM_ERROR(1, "parameters are not correct"),
    CART_EMPTY(18, "cart is empty"),
    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
