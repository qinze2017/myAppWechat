package com.ze.market.repository;

import com.ze.market.dao.CategoryTb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryTbRepository extends JpaRepository<CategoryTb, Integer> {
        List<CategoryTb> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
