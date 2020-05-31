package com.ze.market.service.Impl;

import com.ze.market.converter.OrderTbToOrderDTOConverter;
import com.ze.market.dao.OrderDetail;
import com.ze.market.dao.OrderTb;
import com.ze.market.dao.ProductTb;
import com.ze.market.dto.CartDTO;
import com.ze.market.dto.OrderDTO;
import com.ze.market.enums.OrderStatusEnum;
import com.ze.market.enums.PayStatusEnum;
import com.ze.market.enums.ResultEnum;
import com.ze.market.exception.SellException;
import com.ze.market.repository.OrderDetailRepository;
import com.ze.market.repository.OrderTbRepository;
import com.ze.market.service.OrderService;
import com.ze.market.service.ProductService;
import com.ze.market.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import org.springframework.data.domain.Pageable;
import java.beans.Expression;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @program: market
 * @description: Implements Service Interface
 * @author: Ze QIN
 * @create: 2020-05-23 21:53
 **/
@Service
@Slf4j
public class OrderServiceImpl implements OrderService  {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderTbRepository orderTbRepository;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        String orderId = KeyUtil.createUniqueKey();
        //List<CartDTO> cartDTOList = new ArrayList<>();

        // search product (numbers and pay)
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductTb productTb = productService.findOne(orderDetail.getProductId());
            if (productTb == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            // calculate orders' amount
            orderAmount = productTb.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            //list orders
            orderDetail.setDetailId(KeyUtil.createUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productTb, orderDetail);
            orderDetailRepository.save(orderDetail);

            //CartDTO cartDTO = new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity());
            //cartDTOList.add(cartDTO);
        };

        // write into database (orderTb and orderDetail)
        OrderTb orderTb = new OrderTb();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderTb);
        orderTb.setOrderAmount(orderAmount);
        orderTb.setOrderStatus(OrderStatusEnum.New.getCode());
        orderTb.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderTbRepository.save(orderTb);

        // reduce numbers of products
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());

        productService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {

        OrderTb orderTb= orderTbRepository.findById(orderId).orElse(new OrderTb());
        if(orderTb == null) {
            throw new SellException( ResultEnum.ORDER_NOT_EXIST);
        }

        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException( ResultEnum.ORDERDETAIL_NOT_EXIST);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderTb, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerWechat, Pageable pageable) {

        Page<OrderTb> orderTbPage = orderTbRepository.findByBuyerWechat(buyerWechat, pageable);

        List<OrderDTO> orderDTOList = OrderTbToOrderDTOConverter.convert(orderTbPage.getContent());

        return new PageImpl<OrderDTO>(orderDTOList, pageable, orderTbPage.getTotalElements());

    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {

        OrderTb orderTb = new OrderTb();

        //check order status
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.New.getCode())){
            log.error("order status is not correct, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //modify order status
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO, orderTb);
        OrderTb updateResult = orderTbRepository.save(orderTb);
        if (updateResult == null) {
            log.error("update failed, orderTb={}", orderTb);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        //return into stock
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("there is not order detail, orderDTO={}",orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartDTOList);

        // Refund when payed
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
            //TODO
        }

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {

        //check order status
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.New.getCode())) {
            log.error("order status is not correct, orderID-={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //modify order status
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderTb orderTb = new OrderTb();
        BeanUtils.copyProperties(orderDTO, orderTb);
        OrderTb updateResult = orderTbRepository.save(orderTb);

        if (updateResult == null) {
            log.error("update failed, orderTb={}", orderTb);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //check order status
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.New.getCode())) {
            log.error("order status is not correct, orderID-={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        // check payment status
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("order payment status is not correct, orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }

        //modify payment status
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderTb orderTb = new OrderTb();
        BeanUtils.copyProperties(orderDTO, orderTb);
        OrderTb updateResult = orderTbRepository.save(orderTb);

        if (updateResult == null) {
            log.error("update failed, orderTb={}", orderTb);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        return orderDTO;
    }
}
