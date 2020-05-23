package com.ze.market.dao;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @program: market
 * @description: object
 * @author: Ze QIN
 * @create: 2020-05-21 21:51
 **/
@Entity
@DynamicUpdate
@Data
public class CategoryTb {

    @Id
    @GeneratedValue
    private Integer categoryId;

    private String categoryName;

    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    public CategoryTb() {
    }

    public CategoryTb(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
