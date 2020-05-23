package com.ze.market.repository;

import com.ze.market.dao.CategoryTb;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
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
    @Transactional
    public void saveTest(){
        /*repository.findById(2)
                .ifPresent( data -> {
                    data.setCategoryType(3);
                    repository.save(data);
                });*/


        CategoryTb categoryTb = new CategoryTb("Test8", 3);
        /*categoryTb.setCategoryId(2);
        categoryTb.setCategoryName("test5");
        categoryTb.setCategoryType(3);*/
        CategoryTb result = repository.save(categoryTb);
        Assert.assertNotNull(result);
        //Assert.assertNotEquals(null,result);
    }

    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(2, 3, 4);

        List<CategoryTb> result = repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, result.size());
    }
}