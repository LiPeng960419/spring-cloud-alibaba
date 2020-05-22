package com.lipeng.order.controller;

import com.lipeng.common.ResultVo;
import com.lipeng.domain.Order;
import com.lipeng.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lipeng 910138
 * @Date: 2020/5/22 17:17
 */
@Slf4j
@RestController
public class SeataController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/seata/{id}")
    public ResultVo seata(@PathVariable Integer id) {
        try {
            ResultVo resultVo = orderService.createOrder(id);
            return resultVo;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResultVo.fail(e.getMessage());
        }
    }

}
