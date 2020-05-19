package com.lipeng.order.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lipeng.domain.Order;
import com.lipeng.order.dao.OrderDao;
import com.lipeng.order.service.OrderService;
import com.lipeng.order.service.fallback.OrderServiceFallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: lipeng 910138
 * @Date: 2020/5/19 11:06
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public void saveOrder(Order order) {
        orderDao.save(order);
    }

    // 定义资源 指定资源名称
    @SentinelResource("message")
    @Override
    public String message() {
        return "message";
    }

    @Override
    @SentinelResource(value = "testSentinelResource",
            fallbackClass = OrderServiceFallback.class,
            blockHandlerClass = OrderServiceFallback.class,
            blockHandler = "testSentinelResourceBlockHandler",
            fallback = "testSentinelResourceFallBack")
    public String testSentinelResource(String param) {
        if ("exception".equals(param)) {
            throw new RuntimeException("param is error");
        }
        return "success";
    }

    // 返回值 参数和原方法一致
//    public String testSentinelResourceBlockHandler(String param, BlockException e) {
//        log.error("触发了BlockException", e);
//        return "BlockException:" + param;
//    }
//
//    // 返回值 参数和原方法一致   异常接收范围更大
//    public String testSentinelResourceFallBack(String param, Throwable e) {
//        log.error("触发了Throwable", e);
//        return "Throwable:" + param;
//    }

}