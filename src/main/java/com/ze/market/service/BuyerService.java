package com.ze.market.service;

import com.ze.market.dto.OrderDTO;

public interface BuyerService {

    //search order
    OrderDTO findOrderOne(String wechat, String orderId);

    //cancel order
    OrderDTO cancelOrder(String wechat, String orderId);
}
