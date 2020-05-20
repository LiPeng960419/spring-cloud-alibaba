package com.lipeng.product.controller;

import com.lipeng.common.ResultVo;
import com.lipeng.domain.Product;
import com.lipeng.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lipeng 910138
 * @Date: 2020/5/19 10:54
 */
@Slf4j
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{id}")
    public ResultVo<Product> id(@PathVariable Integer id) {
        log.info("查询商品信息id：{}", id);
        Product product = productService.findById(id);
        return ResultVo.success(product);
    }

}