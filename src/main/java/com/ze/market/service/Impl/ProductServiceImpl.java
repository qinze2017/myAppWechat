package com.ze.market.service.Impl;

import com.ze.market.dao.ProductTb;
import com.ze.market.enums.ProductStatusEnum;
import com.ze.market.repository.ProductTbRepository;
import com.ze.market.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @program: market
 * @description: Implements Service Interface
 * @author: Ze QIN
 * @create: 2020-05-23 14:16
 **/
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductTbRepository repository;

    @Override
    public ProductTb findOne(String productId) {
        return repository.findById(productId).orElse(new ProductTb());
    }

    @Override
    public List<ProductTb> findOnAll() {
        return repository.findByProductStatus(ProductStatusEnum.ON.getCode());
    }

    @Override
    public Page<ProductTb> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductTb save(ProductTb productTb) {
        return repository.save(productTb);
    }
}
