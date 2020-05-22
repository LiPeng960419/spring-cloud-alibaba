package com.lipeng.canal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lipeng 910138
 * @Date: 2020/5/22 16:26
 */
@RestController
@RefreshScope //动态刷新的注解
public class NacosRefreshController {

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Value("${app.name}")
    private String appName;

    @GetMapping("/test1")
    public String test1() {
        return applicationContext.getEnvironment().getProperty("app.name");
    }

    @GetMapping("/test2")
    public String test2() {
        return appName;
    }

}
