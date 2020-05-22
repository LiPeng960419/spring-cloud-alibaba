package com.lipeng.product.service.impl;

import com.lipeng.domain.Product;
import com.lipeng.product.dao.ProductDao;
import com.lipeng.product.service.ProductService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: lipeng 910138
 * @Date: 2020/5/19 11:06
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product findById(Integer id) {
        Product product = productDao.findById(id);
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
        log.info("当前 XID: {}", RootContext.getXID());
        if (product == null) {
            return 0;
        }
        // 先执行sql 再异常
        int result = productDao.desProductCount(product.getId(), product.getStock() - 1);
        // int i = 1 / 0;
        return result;
    }

}