package com.lipeng.order.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @Author: lipeng 910138
 * @Date: 2020/5/19 17:08
 */
@Component
public class RequestOriginParserDefinition implements RequestOriginParser {

    /*
    授权规则  返回的是指定的流控应用名称
     */
    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
//        String serviceName = httpServletRequest.getParameter("serviceName");
//        if (StringUtils.isEmpty(serviceName)) {
//            throw new RuntimeException("serviceName is empty");
//        }
//        return serviceName;
        return null;
    }

}