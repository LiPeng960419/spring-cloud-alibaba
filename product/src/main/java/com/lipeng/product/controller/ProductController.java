package com.lipeng.product.controller;

import com.lipeng.common.ResultVo;
import com.lipeng.domain.Product;
import com.lipeng.product.service.ProductService;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lipeng 910138
 * @Date: 2020/5/19 10:54
 */
@Slf4j
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    /*
    url使用serverTimezone=UTC，最终返回的时间和数据库时间一样，外表看起来没有错，但是实际在整个逻辑流转中时间都是以多8小时存在。
    即：读取的时间是错的，比数据库多8小时，返回的时间又是对的，与数据库一样。

    原因：指定url为UTC时区去数据库查时间，返回时间会在数据库记录中当前时间的基础上加上系统时区时间(mysql时区默认是系统时区)，即n+8。
    但是@ResponseBody返回数据时，使用的是jackson。jackson默认使用的是UTC时区，又减去8小时，所以外表看起来没有错。

    标准做法：
    1.指定url的serverTimezone=Asia/Shanghai保证读取的时间正确。
      表示以+8时区查询mysql数据库，因为mysql时区默认的就是系统时区(咱们的系统时区就是+8)】
    2.指定spring.jackson.time-zone=Asia/Shanghai保证返回时间正确。【否则使用默认UTC时区，对当前正确时间减去8小时
      注意：在使用spring.jackson.time-zone，必须先使用spring.jackson.date-format
     */

    @GetMapping("/product/{id}")
    public ResultVo<Product> id(@PathVariable Integer id) {
        log.info("查询商品信息id：{}", id);
        Product product = productService.findById(id);
        return ResultVo.success(product);
    }

    @GetMapping("/product/findbyId/{id}")
    public Product findbyId(@PathVariable Integer id) {
        Product product = productService.findById(id);
        return product;
    }

    @GetMapping("/product/dateTest")
    public ResultVo findById(@RequestParam Date date) {
        log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
        return ResultVo.success();
    }

    @PostMapping("/product/save")
    public ResultVo save(@RequestBody Product product) {
        product.setCreateTime(new Date());
        productService.save(product);
        return ResultVo.success();
    }

}