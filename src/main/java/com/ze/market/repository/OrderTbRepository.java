package com.ze.market.repository;

import com.ze.market.dao.OrderTb;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderTbRepository extends JpaRepository<OrderTb, String> {

    Page<OrderTb> findByBuyerWechat(String buyerWechat, Pageable pageable);
}
