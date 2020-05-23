package com.ze.market.service.Impl;

import com.ze.market.dao.CategoryTb;
import com.ze.market.repository.CategoryTbRepository;
import com.ze.market.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: market
 * @description: Implements Service Interface
 * @author: Ze QIN
 * @create: 2020-05-23 00:44
 **/
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryTbRepository repository;

    @Override
    public CategoryTb findOne(Integer categoryId) {
        return repository.findById(categoryId)
                .orElse(new CategoryTb());
    }

    @Override
    public List<CategoryTb> findAll() {
        return repository.findAll();
    }

    @Override
    public List<CategoryTb> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public CategoryTb save(CategoryTb categoryTb) {
        return repository.save(categoryTb);
    }
}
