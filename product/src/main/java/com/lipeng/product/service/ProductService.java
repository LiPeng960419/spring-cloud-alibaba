package com.lipeng.product.service;

import com.lipeng.domain.Product;

/**
 * @Author: lipeng 910138
 * @Date: 2020/5/19 11:06
 */
public interface ProductService {

    Product findById(Integer id);

    void save(Product product);

    int desProductCount(Product product);
}