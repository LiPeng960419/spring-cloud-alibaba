package com.lipeng.product.service.impl;

import com.lipeng.domain.Product;
import com.lipeng.product.dao.ProductDao;
import com.lipeng.product.service.ProductService;
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
        Product product = productDao.findById(id).get();
        if (product == null) {
            return null;
        }
        return product;
    }


    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    public int desProductCount(Product product) {
        if (product == null) {
            return 0;
        }
        return productDao.desProductCount(product.getId(), product.getStock() - 1);
    }

}