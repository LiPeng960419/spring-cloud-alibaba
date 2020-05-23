package com.lipeng.order.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.lipeng.common.ResultVo;
import com.lipeng.domain.Order;
import com.lipeng.domain.Product;
import com.lipeng.feign.ProductFeignSerivce;
import com.lipeng.order.dao.OrderDao;
import com.lipeng.order.service.OrderService;
import com.lipeng.order.service.fallback.OrderServiceFallback;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
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

    @Autowired
    private ProductFeignSerivce productFeignSerivce;

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

    @Override
    @GlobalTransactional
    public ResultVo createOrder(Integer id) {
        try {
            log.info("当前 XID: {}", RootContext.getXID());
            ResultVo<Product> resultVo = productFeignSerivce.findById(id);
            if (!"200".equals(resultVo.getReturnCode())) {
                return ResultVo.fail("查询商品信息失败");
            }
            Product p = resultVo.getData();
            log.info("product:{}", p);
            Order order = new Order();
            order.setName(p.getName());
            order.setPrice(p.getPrice());
            orderDao.save(order);
            ResultVo desResultVo = productFeignSerivce.desProductCount(p);
            // 如果通过feign调用后根据返回结果判断是否成功 可以通过抛出异常 回滚全局事务
            // 如果不判断返回结果且调用服务超时 那就在sentinel fallback里面回滚全局事务
            if (!"200".equals(desResultVo.getReturnCode())) {
                throw new RuntimeException("库存修改失败啦,回滚");
            }
            if (id.equals(1)){
                throw new RuntimeException("手动测试回滚");
            }
            return ResultVo.success(order);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
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