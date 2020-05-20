package com.lipeng.fallback;

import com.lipeng.common.ResultVo;
import com.lipeng.domain.Product;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: lipeng 910138
 * @Date: 2020/5/20 13:53
 */
@Slf4j
@Component
public class ProductFeignSerivceFallbackFactory implements FallbackFactory<ProductFeignSerivce> {

    @Override
    public ProductFeignSerivce create(Throwable throwable) {
        return new ProductFeignSerivce() {

            @Override
            public ResultVo<Product> findById(Integer id) {
                log.error("ProductFeignSerivce findById error", throwable);
                return ResultVo.fail("Product findById fail");
            }

            @Override
            public ResultVo dateTest(Date date) {
                log.error("ProductFeignSerivce dateTest error", throwable);
                return ResultVo.fail("dateTest fail");
            }
        };
    }

}