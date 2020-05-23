package com.ze.market.service.Impl;

import com.ze.market.dao.CategoryTb;
import com.ze.market.repository.CategoryTbRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    void findOne() throws Exception {
        CategoryTb categoryTb = categoryService.findOne(1);
        Assert.assertEquals(new Integer(1), categoryTb.getCategoryId());
    }

    @Test
    void findAll() throws Exception {
        List<CategoryTb> categoryTbList = categoryService.findAll();
        Assert.assertNotEquals(0, categoryTbList.size());
    }

    @Test
    void findByCategoryTypeIn() throws Exception {
        List<CategoryTb> categoryTbList = categoryService.findByCategoryTypeIn(Arrays.asList(1,2,3,4));
        Assert.assertNotEquals(0, categoryTbList);
    }

    @Test
    void save() throws Exception {
        CategoryTb categoryTb = new CategoryTb("test88",10);
        CategoryTb result = categoryService.save(categoryTb);
        Assert.assertNotNull(result);
    }
}