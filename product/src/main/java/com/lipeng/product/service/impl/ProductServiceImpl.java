package com.lipeng.product.service.impl;

import com.lipeng.domain.Product;
import com.lipeng.product.dao.ProductDao;
import com.lipeng.product.service.ProductService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: lipeng 910138
 * @Date: 2020/5/19 11:06
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product findById(Integer id) {
        return Optional.of(productDao.findById(id)).get().orElseGet(null);
    }

}