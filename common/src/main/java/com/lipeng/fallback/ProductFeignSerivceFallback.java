package com.lipeng.fallback;

import com.lipeng.common.ResultVo;
import com.lipeng.domain.Product;
import com.lipeng.feign.ProductFeignSerivce;
import java.util.Date;
import org.springframework.stereotype.Service;

/**
 * @Author: lipeng 910138
 * @Date: 2020/5/20 10:49
 */
@Service
public class ProductFeignSerivceFallback implements ProductFeignSerivce {

    @Override
    public ResultVo<Product> findById(Integer id) {
        return ResultVo.fail("Product findById fail");
    }

    @Override
    public ResultVo dateTest(Date date) {
        return ResultVo.fail("dateTest fail");
    }

}