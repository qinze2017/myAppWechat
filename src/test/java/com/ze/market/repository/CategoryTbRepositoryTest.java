package com.ze.market.repository;

import com.ze.market.dao.CategoryTb;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryTbRepositoryTest {

    @Autowired
    private CategoryTbRepository repository;

    @Test
    public void findOneTest(){

        repository.findById(1)
                .ifPresent(data -> {
                    System.out.println(data);
                });
    }

    @Test
    public void saveTest(){
        /*repository.findById(2)
                .ifPresent( data -> {
                    data.setCategoryType(3);
                    repository.save(data);
                });*/


        CategoryTb categoryTb = new CategoryTb();
        categoryTb.setCategoryId(2);
        categoryTb.setCategoryName("test5");
        categoryTb.setCategoryType(3);

    }
}