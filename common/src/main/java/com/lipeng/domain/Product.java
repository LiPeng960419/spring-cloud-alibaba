package com.lipeng.domain;

import java.util.Date;
import lombok.Data;

/**
 * @Author: lipeng 910138
 * @Date: 2020/5/19 10:58
 */
@Data
public class Product {

    private Integer id;

    private String name;

    private Integer stock;

    private Long price; // 分

    private Date createTime;

}