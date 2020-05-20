package com.lipeng.feign;

import com.lipeng.common.ResultVo;
import com.lipeng.domain.Product;
import com.lipeng.fallback.ProductFeignSerivceFallback;
import com.lipeng.fallback.ProductFeignSerivceFallbackFactory;
import java.util.Date;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: lipeng 910138
 * @Date: 2020/5/19 14:04
 * fallback fallbackFactory二选一
 */
@FeignClient(value = "shop-product",
        //fallback = ProductFeignSerivceFallback.class,
        fallbackFactory = ProductFeignSerivceFallbackFactory.class)
public interface ProductFeignSerivce {

    @GetMapping("/product/{id}")
    ResultVo<Product> findById(@PathVariable Integer id);

    @GetMapping("/product/dateTest")
    ResultVo dateTest(@RequestParam Date date);

}