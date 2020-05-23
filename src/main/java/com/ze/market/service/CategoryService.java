package com.ze.market.service;

import com.ze.market.dao.CategoryTb;

import java.util.List;

public interface CategoryService {

    CategoryTb findOne(Integer categoryId);

    List<CategoryTb> findAll();

    List<CategoryTb> findByCategoryTypeIn(List<Integer> categoryTypeList);

    CategoryTb save(CategoryTb categoryTb);
}
