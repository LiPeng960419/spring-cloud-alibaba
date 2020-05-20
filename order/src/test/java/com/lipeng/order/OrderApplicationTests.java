package com.lipeng.order;

import com.lipeng.common.ResultVo;
import com.lipeng.feign.ProductFeignSerivce;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class OrderApplicationTests {

    @Autowired
    private ProductFeignSerivce productFeignSerivce;

    @Test
    public void contextLoads() {
        ResultVo resultVo = productFeignSerivce.dateTest(new Date());
        log.info(resultVo.toString());
    }

}