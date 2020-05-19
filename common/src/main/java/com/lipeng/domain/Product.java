package com.lipeng.domain;

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
@Table(name = "t_producet")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer stock;

    private Long price; // 分

}