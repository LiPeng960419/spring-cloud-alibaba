package com.lipeng.order.controller;

import com.lipeng.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lipeng 910138
 * @Date: 2020/5/19 16:36
 */
@RestController
public class SentinelController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order/message1")
    public String message1() {
        orderService.message();
        return "message1";
    }

    @GetMapping("/order/message2")
    public String message2() {
        orderService.message();
        return "message2";
    }

}