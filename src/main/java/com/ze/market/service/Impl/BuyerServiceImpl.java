package com.ze.market.service.Impl;

import com.ze.market.dto.OrderDTO;
import com.ze.market.enums.ResultEnum;
import com.ze.market.exception.SellException;
import com.ze.market.service.BuyerService;
import com.ze.market.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: market
 * @description: Implements Service Interface
 * @author: Ze QIN
 * @create: 2020-05-31 20:00
 **/
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String wechat, String orderId) {

        return checkOrderOwner(wechat, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String wechat, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(wechat, orderId);
        if(orderDTO == null) {
            log.error("can not find the order, orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String wechat, String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);

        if(orderDTO == null) {
            return null;
        }

        if(!orderDTO.getBuyerWechat().equalsIgnoreCase(wechat)){
            log.error(" orders' wechat are not same. wechat={}, orderDTO={}", wechat, orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
