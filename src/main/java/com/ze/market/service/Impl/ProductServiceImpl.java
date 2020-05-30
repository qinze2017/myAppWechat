package com.ze.market.service.Impl;

import com.ze.market.dao.ProductTb;
import com.ze.market.dto.CartDTO;
import com.ze.market.enums.ProductStatusEnum;
import com.ze.market.enums.ResultEnum;
import com.ze.market.exception.SellException;
import com.ze.market.repository.ProductTbRepository;
import com.ze.market.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {

    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO: cartDTOList) {
            ProductTb productTb = repository.findById(cartDTO.getProductId()).orElse(new ProductTb());
            if (productTb == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST)
            }

            Integer result = productTb.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productTb.setProductStock(result);

            repository.save(productTb);
        }
    }


}
