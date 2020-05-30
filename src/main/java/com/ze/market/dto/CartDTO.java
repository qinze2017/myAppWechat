package com.ze.market.dto;

import lombok.Data;

/**
 * @program: market
 * @description:
 * @author: Ze QIN
 * @create: 2020-05-29 21:34
 **/
@Data
public class CartDTO {

    private String productId;

    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
