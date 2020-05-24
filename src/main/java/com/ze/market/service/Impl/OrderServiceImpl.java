package com.ze.market.service.Impl;

import com.ze.market.dao.OrderDetail;
import com.ze.market.dao.OrderTb;
import com.ze.market.dao.ProductTb;
import com.ze.market.dto.OrderDTO;
import com.ze.market.enums.ResultEnum;
import com.ze.market.exception.SellException;
import com.ze.market.repository.OrderDetailRepository;
import com.ze.market.repository.OrderTbRepository;
import com.ze.market.service.OrderService;
import com.ze.market.service.ProductService;
import com.ze.market.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.math.BigInteger;

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

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderTbRepository orderTbRepository;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {

        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        String orderId = KeyUtil.createUniqueKey();

        // search product (numbers and pay)
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductTb productTb = productService.findOne(orderDetail.getProductId());
            if (productTb == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            // calculate orders' amount
            orderAmount = orderDetail.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            //list orders
            orderDetail.setDetailId(KeyUtil.createUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productTb, orderDetail);
            orderDetailRepository.save(orderDetail);
        };

        // write into database (orderTb and orderDetail)
        OrderTb orderTb = new OrderTb();
        orderTb.setOrderId(orderId);
        orderTb.setOrderAmount(orderAmount);
        BeanUtils.copyProperties(orderDTO, orderTb);
        orderTbRepository.save(orderTb);
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
