package com.ze.market.VO;

import lombok.Data;

/**
 * @program: market
 * @description: http return objects view
 * @author: Ze QIN
 * @create: 2020-05-23 15:05
 **/
@Data
public class ResultVO<T> {

    /**wrong code */
    private Integer code;

    private String msg;

    private T data;
}
