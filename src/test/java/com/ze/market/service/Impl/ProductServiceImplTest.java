package com.ze.market.service.Impl;

import com.ze.market.dao.ProductTb;
import com.ze.market.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    ProductServiceImpl productService;

    @Test
    void findOne() throws Exception {

        ProductTb productTb = productService.findOne("123456");
        Assert.assertEquals("123456", productTb.getProductId());
    }

    @Test
    void findOnAll() throws Exception {
        List<ProductTb> productTbList = productService.findOnAll();
        Assert.assertNotEquals(0, productTbList.size());
    }

    @Test
    void findAll() throws Exception {

        PageRequest request = PageRequest.of(0, 2);

        Page<ProductTb> productTbPage = productService.findAll(request);
        //System.out.println(productTbPage.getTotalElements());
        Assert.assertNotEquals(0, productTbPage.getTotalElements());
    }

    @Test
    void save() throws Exception {
        ProductTb productTb = new ProductTb();
        productTb.setProductId("888");
        productTb.setProductName("Orange");
        productTb.setProductPrice(new BigDecimal(3.2));
        productTb.setProductStock(100);
        productTb.setProductDescription("Sweet");
        productTb.setProductIcon("https://xxx.jpg");
        productTb.setProductStatus(ProductStatusEnum.OFF.getCode());
        productTb.setCategoryType(2);

        ProductTb result = productService.save(productTb);
        Assert.assertNotNull(result);
    }
}