package com.ze.market.repository;

import com.ze.market.dao.OrderTb;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTbRepositoryTest {

    @Autowired
    private OrderTbRepository repository;

    private final String OPENID = "100111";

    @Test
    public void saveTest(){
        OrderTb orderTb = new OrderTb();
        orderTb.setOrderId("1234567");
        orderTb.setBuyerName("ZX");
        orderTb.setBuyerPhone("5146258888");
        orderTb.setBuyerAddress("Quebec");
        orderTb.setBuyerWechat(OPENID);
        orderTb.setOrderAmount(new BigDecimal(2.8));

        OrderTb result = repository.save(orderTb);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerWechat() throws Exception {

        PageRequest pageRequest = PageRequest.of(1,3);

        Page<OrderTb> result = repository.findByBuyerWechat(OPENID, pageRequest);
        Assert.assertNotEquals(0, result.getTotalElements());
        System.out.println(result.getTotalElements());
    }
}