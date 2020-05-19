package com.lipeng.order.dao;

import com.lipeng.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: lipeng 910138
 * @Date: 2020/5/19 11:05
 */
public interface OrderDao extends JpaRepository<Order, Integer>,
        JpaSpecificationExecutor<Order> {

}
