package com.lipeng.gateway.filters;

import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author: lipeng 910138
 * @Date: 2020/5/20 15:52
 */
// 自定义局部过滤器
@Slf4j
@Component
public class LogGatewayFilterFactory extends
        AbstractGatewayFilterFactory<LogGatewayFilterFactory.Config> {

    public LogGatewayFilterFactory() {
        super(LogGatewayFilterFactory.Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                if (config.isConsoleLog()) {
                    log.info("consoleLog已经开启了");
                }
                if (config.isCacheLog()) {
                    log.info("cacheLog已经开启了");
                }
                return chain.filter(exchange);
            }
        };
    }

    // 读取配置文件参数
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("consoleLog", "cacheLog");
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Config {

        private boolean consoleLog;
        private boolean cacheLog;
    }

}