package com.lipeng.order.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSON;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @Author: lipeng 910138
 * @Date: 2020/5/19 17:15
 */
@Component
public class SentinelExceptionHandler implements UrlBlockHandler {

    @Override
    public void blocked(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse, BlockException e) throws IOException {
        httpServletResponse.setContentType("application/json; charset=utf-8");
        httpServletResponse.setCharacterEncoding("utf-8");
        ResponseData responseData = null;
        if (e instanceof FlowException) {
            responseData = new ResponseData(-1, "限流异常");
        } else if (e instanceof DegradeException) {
            responseData = new ResponseData(-2, "降级异常");
        } else if (e instanceof ParamFlowException) {
            responseData = new ResponseData(-3, "限流异常");
        } else if (e instanceof AuthorityException) {
            responseData = new ResponseData(-4, "授权异常");
        } else if (e instanceof SystemBlockException) {
            responseData = new ResponseData(-5, "系统负载异常");
        } else {
            responseData = new ResponseData(-6, "未知异常");
        }

        httpServletResponse.getWriter().write(JSON.toJSONString(responseData));
    }

}

@NoArgsConstructor
@AllArgsConstructor
@Data
class ResponseData {

    private int code;
    private String message;
}