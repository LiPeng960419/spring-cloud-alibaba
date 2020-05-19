package com.lipeng.order.service.fallback;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: lipeng 910138
 * @Date: 2020/5/19 17:52
 */
@Slf4j
public class OrderServiceFallback {

    /*
    方法必须是static的 否则无效
     */

    // 返回值 参数和原方法一致
    public static String testSentinelResourceBlockHandler(String param, BlockException e) {
        log.error("OrderServiceFallback触发了BlockException", e);
        return "BlockException:" + param;
    }

    // 返回值 参数和原方法一致   异常接收范围更大
    public static String testSentinelResourceFallBack(String param, Throwable e) {
        log.error("OrderServiceFallback触发了Throwable", e);
        return "Throwable:" + param;
    }

}
