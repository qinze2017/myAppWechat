package com.ze.market.dto;

import com.ze.market.dao.OrderDetail;
import com.ze.market.enums.OrderStatusEnum;
import com.ze.market.enums.PayStatusEnum;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @program: market
 * @description: order data transfer objects
 * @author: Ze QIN
 * @create: 2020-05-23 21:48
 **/
@Data
public class OrderDTO {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerWechat;

    private BigDecimal orderAmount;

    private Date createTime;

    private Date updateTime;

    private List<OrderDetail> orderDetailList;
}
