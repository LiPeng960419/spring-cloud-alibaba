package com.lipeng.product;

import com.lipeng.product.dao.ProductDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductApplicationTests {

    @Autowired
    private ProductDao productDao;

    @Test
    public void contextLoads() {
        productDao.desProductCount(1,999);
    }

}