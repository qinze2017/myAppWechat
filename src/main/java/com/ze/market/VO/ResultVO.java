package com.ze.market.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @program: market
 * @description: http return objects view
 * @author: Ze QIN
 * @create: 2020-05-23 15:05
 **/
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO<T> {

    /**wrong code */
    private Integer code;

    private String msg;

    private T data;
}
