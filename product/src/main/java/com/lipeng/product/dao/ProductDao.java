package com.lipeng.product.dao;

import com.lipeng.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: lipeng 910138
 * @Date: 2020/5/19 11:05
 */
public interface ProductDao extends JpaRepository<Product, Integer>,
        JpaSpecificationExecutor<Product> {

}
