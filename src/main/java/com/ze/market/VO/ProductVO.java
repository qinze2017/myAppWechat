package com.ze.market.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @program: market
 * @description: product info
 * @author: Ze QIN
 * @create: 2020-05-23 15:41
 **/
@Data
public class ProductVO {

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductTbVO> productTbVOList;
}
