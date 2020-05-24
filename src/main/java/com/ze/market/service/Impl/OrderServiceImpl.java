package com.ze.market.service.Impl;

import com.ze.market.dao.ProductTb;
import com.ze.market.dto.OrderDTO;
import com.ze.market.service.OrderService;
import com.ze.market.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

/**
 * @program: market
 * @description: Implements Service Interface
 * @author: Ze QIN
 * @create: 2020-05-23 21:53
 **/
@Service
public class OrderServiceImpl implements OrderService  {

    @Autowired
    private ProductService productService;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {

        // search product (numbers and pay)
        orderDTO.getOrderDetailList().stream().forEach(o -> {
            ProductTb productTb = productService.findOne(o.getProductId());
            if (productTb == null) {

            }
        });

        // calculate amount

        // write into database (orderTb and orderDetail)

        // reduce numbers of products

        return null;
    }

    @Override
    public OrderDTO findOne(String OrderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
