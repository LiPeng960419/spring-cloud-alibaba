package com.lipeng.order.service.impl;

import com.lipeng.domain.Order;
import com.lipeng.order.dao.OrderDao;
import com.lipeng.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: lipeng 910138
 * @Date: 2020/5/19 11:06
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public void saveOrder(Order order) {
        orderDao.save(order);
    }
}