package com.lipeng.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * @Author: lipeng 910138
 * @Date: 2020/5/19 10:58
 */
@Data
@Entity
@Table(name = "t_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer stock;

    private Long price; // 分

    @Column(name = "create_time")
    private Date createTime;

}