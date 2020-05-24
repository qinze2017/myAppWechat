package com.ze.market.exception;

import com.ze.market.enums.ResultEnum;

/**
 * @program: market
 * @description: exceptions
 * @author: Ze QIN
 * @create: 2020-05-23 21:59
 **/
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());

        this.code = resultEnum.getCode();
    }
}
