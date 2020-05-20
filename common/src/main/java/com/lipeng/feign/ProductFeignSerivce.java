package com.lipeng.feign;

import com.lipeng.common.ResultVo;
import com.lipeng.domain.Product;
import com.lipeng.fallback.ProductFeignSerivceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: lipeng 910138
 * @Date: 2020/5/19 14:04
 */
@FeignClient(value = "product", fallback = ProductFeignSerivceFallback.class)
public interface ProductFeignSerivce {

    @GetMapping("/product/{id}")
    ResultVo<Product> findById(@PathVariable Integer id);

}