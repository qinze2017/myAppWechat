package com.ze.market.dao;

import com.ze.market.enums.OrderStatusEnum;
import com.ze.market.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @program: market
 * @description: Order
 * @author: Ze QIN
 * @create: 2020-05-23 20:23
 **/

@Entity
@Data
@DynamicUpdate
public class OrderTb {

    @Id
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerWechat;

    private BigDecimal orderAmount;

    private Integer orderStatus = OrderStatusEnum.New.getCode();

    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    private Date createTime;

    private Date updateTime;

}
