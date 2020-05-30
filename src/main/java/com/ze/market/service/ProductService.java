package com.ze.market.service;

import com.ze.market.dao.ProductTb;
import com.ze.market.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductTb findOne(String productId);

    List<ProductTb> findOnAll();

    Page<ProductTb> findAll(Pageable pageable);

    ProductTb save(ProductTb productTb);

    void increaseStock(List<CartDTO> cartDTOList);

    void decreaseStock(List<CartDTO> cartDTOList);

}
