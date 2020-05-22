package com.lipeng.product.dao;

import com.lipeng.domain.Product;

/**
 * @Author: lipeng 910138
 * @Date: 2020/5/19 11:05
 */
public interface ProductDao {

    int desProductCount(Integer id, Integer stock);

    Product findById(Integer id);

    void save(Product product);
}