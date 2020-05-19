package com.lipeng.order.service;

import com.lipeng.domain.Order;

/**
 * @Author: lipeng 910138
 * @Date: 2020/5/19 11:06
 */
public interface OrderService {

    void saveOrder(Order order);

    String message();

    String testSentinelResource(String param);

}