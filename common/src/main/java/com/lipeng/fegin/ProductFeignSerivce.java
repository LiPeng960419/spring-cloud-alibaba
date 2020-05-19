package com.lipeng.fegin;

import com.lipeng.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: lipeng 910138
 * @Date: 2020/5/19 14:04
 */
@FeignClient("product")
public interface ProductFeignSerivce {

    @GetMapping("/product/{id}")
    Product findById(@PathVariable Integer id);

}