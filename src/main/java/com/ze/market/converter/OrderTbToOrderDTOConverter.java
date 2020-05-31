package com.ze.market.converter;

import com.ze.market.dao.OrderTb;
import com.ze.market.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: market
 * @description: converter
 * @author: Ze QIN
 * @create: 2020-05-30 22:07
 **/
public class OrderTbToOrderDTOConverter {

    public static OrderDTO convert(OrderTb orderTb) {

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderTb,orderDTO);

        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderTb> orderTbList){

        return orderTbList.stream().map( e ->
                convert(e)
                ).collect(Collectors.toList());

    }
}
