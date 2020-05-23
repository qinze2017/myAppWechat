package com.ze.market.repository;

import com.ze.market.dao.ProductTb;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTbRepositoryTest {

    @Autowired
    ProductTbRepository repository;

    @Test
    public void saveTest() {
        ProductTb productTb = new ProductTb();
        productTb.setProductId("123456");
        productTb.setProductName("Apple");
        productTb.setProductPrice(new BigDecimal(2.2));
        productTb.setProductStock(100);
        productTb.setProductDescription("Sweet red big");
        productTb.setProductIcon("https://xxx.jpg");
        productTb.setProductStatus(0);
        productTb.setCategoryType(2);

        ProductTb result = repository.save(productTb);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByProductStatus() throws Exception {
        List<ProductTb> productTbList = repository.findByProductStatus(0);
        Assert.assertNotEquals(0, productTbList.size());
    }
}