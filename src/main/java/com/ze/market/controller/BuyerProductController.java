package com.ze.market.controller;

import com.ze.market.VO.ProductTbVO;
import com.ze.market.VO.ProductVO;
import com.ze.market.VO.ResultVO;
import com.ze.market.dao.CategoryTb;
import com.ze.market.dao.ProductTb;
import com.ze.market.service.CategoryService;
import com.ze.market.service.ProductService;
import com.ze.market.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: market
 * @description: Controller
 * @author: Ze QIN
 * @create: 2020-05-23 14:59
 **/

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list() {

        // checkout all the product on the shelf
        List<ProductTb> productTbList = productService.findOnAll();

        // find all categories one time
        List<Integer> categoryTypeList = new ArrayList<>();
        productTbList.stream().forEach(p -> {
            categoryTypeList.add(p.getCategoryType());
        });
        List<CategoryTb> categoryTbList = categoryService.findByCategoryTypeIn(categoryTypeList);

        // Data assembly
        List<ProductVO> productVOList = new ArrayList<>();
        categoryTbList.stream().forEach( c -> {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(c.getCategoryType());
            productVO.setCategoryName(c.getCategoryName());

            List<ProductTbVO> productTbVOList = new ArrayList<>();
            productTbList.stream().forEach(p -> {
                if(p.getCategoryType().equals(c.getCategoryType())) {
                    ProductTbVO productTbVO = new ProductTbVO();
                    BeanUtils.copyProperties(p, productTbVO);
                    productTbVOList.add(productTbVO);
                }
            });
            productVO.setProductTbVOList(productTbVOList);
            productVOList.add(productVO);
        });

        ResultVO resultVO = new ResultVO();
        /*ProductVO productVO = new ProductVO();
        ProductTbVO productTbVO = new ProductTbVO();

        productVO.setProductTbVOList(Arrays.asList(productTbVO));*/
        //resultVO.setCode(0);
        //resultVO.setMsg("success");
        //resultVO.setData(Arrays.asList(productVO));
        //resultVO.setData(productVOList);

        return ResultVOUtil.success(productVOList);
    }
}
