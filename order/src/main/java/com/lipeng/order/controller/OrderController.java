package com.lipeng.order.controller;

import com.lipeng.domain.Order;
import com.lipeng.domain.Product;
import com.lipeng.fegin.ProductFeignSerivce;
import com.lipeng.order.service.OrderService;
import java.util.List;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: lipeng 910138
 * @Date: 2020/5/19 10:54
 */
@Slf4j
@RestController
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderService orderService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ProductFeignSerivce productFeignSerivce;

    @GetMapping("/order/create/fegin/{id}")
    public Order fegin(@PathVariable Integer id) {
        log.info("查询商品信息id：{}", id);
        Product p = productFeignSerivce.findById(id);
        log.info("product:{}", p);
        Order order = new Order();
        order.setName(p.getName());
        order.setPrice(p.getPrice());
        orderService.saveOrder(order);
        return order;
    }

    @GetMapping("/order/create/ribbon/{id}")
    public Order ribbon(@PathVariable Integer id) {
        log.info("查询商品信息id：{}", id);
        Product p = restTemplate.getForObject("http://product/product/" + id, Product.class);
        log.info("product:{}", p);
        Order order = new Order();
        order.setName(p.getName());
        order.setPrice(p.getPrice());
        orderService.saveOrder(order);
        return order;
    }

    @GetMapping("/order/create/{id}")
    public Order create(@PathVariable Integer id) {
        log.info("查询商品信息id：{}", id);
        List<ServiceInstance> productRpc = discoveryClient.getInstances("product");
        ServiceInstance instance = productRpc.get(new Random().nextInt(productRpc.size()));
        Product product = restTemplate.getForObject("http://" + instance.getHost() +
                ":" + instance.getPort() + "/product/" + id, Product.class);
        log.info("ServiceInstance:{}", product);
        Product p = restTemplate.getForObject("http://127.0.0.1:8081/product/" + id, Product.class);
        log.info("product:{}", p);
        Order order = new Order();
        order.setName(p.getName());
        order.setPrice(p.getPrice());
        orderService.saveOrder(order);
        return order;
    }

}