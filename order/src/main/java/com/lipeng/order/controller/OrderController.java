package com.lipeng.order.controller;

import com.lipeng.common.ResultVo;
import com.lipeng.domain.Order;
import com.lipeng.domain.Product;
import com.lipeng.feign.ProductFeignSerivce;
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
    public ResultVo fegin(@PathVariable Integer id) {
        log.info("查询商品信息id：{}", id);
        ResultVo<Product> resultVo = productFeignSerivce.findById(id);
        Product p = resultVo.getData();
        log.info("product:{}", p);
        Order order = new Order();
        order.setName(p.getName());
        order.setPrice(p.getPrice());
        orderService.saveOrder(order);
        return ResultVo.success(order);
    }

    /*
    发现使用Ribbon的时候，必须要在RestTemplate bean配置中添加负载均衡注解@LoadBalanced
     */
    @GetMapping("/order/create/ribbon/{id}")
    public ResultVo ribbon(@PathVariable Integer id) {
        log.info("查询商品信息id：{}", id);
        Product p = restTemplate.getForObject("http://product/product/findbyId/" + id, Product.class);
        log.info("product:{}", p);
        Order order = new Order();
        order.setName(p.getName());
        order.setPrice(p.getPrice());
        orderService.saveOrder(order);
        return ResultVo.success(order);
    }

    /*
    加了了注解 @LoadBalanced 之后，我们的restTemplate 会走这个类RibbonLoadBalancerClient
    这里通过instance查询的时候 不能加上@LoadBalanced
     */
    @GetMapping("/order/create/{id}")
    public ResultVo create(@PathVariable Integer id) {
        log.info("查询商品信息id：{}", id);
        List<ServiceInstance> productRpc = discoveryClient.getInstances("product");
        ServiceInstance instance = productRpc.get(new Random().nextInt(productRpc.size()));
        Product product = restTemplate.getForObject("http://" + instance.getHost() + ":" + instance.getPort() + "/product/findbyId/" + id, Product.class);
        log.info("ServiceInstance:{}", product);
        Product p = restTemplate.getForObject("http://127.0.0.1:8081/product/findbyId/" + id, Product.class);
        log.info("product:{}", p);
        Order order = new Order();
        order.setName(p.getName());
        order.setPrice(p.getPrice());
        orderService.saveOrder(order);
        return ResultVo.success(order);
    }

}