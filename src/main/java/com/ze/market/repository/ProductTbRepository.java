package com.ze.market.repository;

import com.ze.market.dao.ProductTb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductTbRepository extends JpaRepository<ProductTb, String> {

    List<ProductTb> findByProductStatus(Integer productStatus);

}
