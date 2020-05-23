package com.ze.market.dao;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.transaction.Transactional;
import java.math.BigDecimal;

/**
 * @program: market
 * @description: Product
 * @author: Ze QIN
 * @create: 2020-05-23 13:35
 **/
@Entity
@Data
public class ProductTb {

    @Id
    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    private String productIcon;

    private Integer productStatus;

    private Integer categoryType;
}
