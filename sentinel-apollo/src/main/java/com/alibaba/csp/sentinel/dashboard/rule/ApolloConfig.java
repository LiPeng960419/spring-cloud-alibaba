package com.alibaba.csp.sentinel.dashboard.rule;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.fastjson.JSON;
import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: lipeng 910138
 * @Date: 2019/10/22 17:16
 */
@Configuration
public class ApolloConfig {

    public static final String APOLLO = "apollo";

    @Value("${apollo.url}")
    private String url;

    @Value("${apollo.token}")
    private String token;

    @Bean
    public Converter<List<FlowRuleEntity>, String> flowRuleEntityEncoder() {
        return JSON::toJSONString;
    }

    @Bean
    public Converter<String, List<FlowRuleEntity>> flowRuleEntityDecoder() {
        return s -> JSON.parseArray(s, FlowRuleEntity.class);
    }

    @Bean
    public ApolloOpenApiClient apolloOpenApiClient() {
        ApolloOpenApiClient client = ApolloOpenApiClient.newBuilder()
                .withPortalUrl(url)
                .withToken(token)
                .build();
        return client;
    }

}