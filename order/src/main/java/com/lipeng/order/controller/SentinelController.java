package com.lipeng.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.lipeng.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lipeng 910138
 * @Date: 2020/5/19 16:36
 */
@RestController
@Slf4j
public class SentinelController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order/message0")
    public String message0() {
        return "message0";
    }

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

    //限流到参数级别上面 或者 对某个参数值限流
    @GetMapping("/order/message3")
    @SentinelResource("message3")
    public String message3(String name, Integer age) {
        log.info(name + "," + age);
        return "message3:" + name + "," + age;
    }

}