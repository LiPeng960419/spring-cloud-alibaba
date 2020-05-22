package com.lipeng.product.dao;

import com.lipeng.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: lipeng 910138
 * @Date: 2020/5/19 11:05
 */
public interface ProductDao extends JpaRepository<Product, Integer>,
        JpaSpecificationExecutor<Product> {

    @Modifying
    @Transactional
    @Query("update Product c set c.stock = :stock where c.id = :id")
    int desProductCount(@Param("id") Integer id, @Param("stock") Integer stock);

}