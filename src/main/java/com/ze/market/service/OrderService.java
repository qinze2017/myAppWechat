package com.ze.market.service;

import com.ze.market.dto.OrderDTO;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;

public interface OrderService {

        OrderDTO create(OrderDTO orderDTO);

        OrderDTO findOne(String OrderId);

        Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

        OrderDTO cancel(OrderDTO orderDTO);

        OrderDTO finish(OrderDTO orderDTO);

        OrderDTO paid(OrderDTO orderDTO);
}
