package com.lipeng.common;

import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.tm.api.GlobalTransaction;
import io.seata.tm.api.GlobalTransactionContext;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @Author: lipeng 910138
 * @Date: 2020/5/24 1:01
 * 用于处理程序调用发生异常的时候由于异常被处理以后无法触发事务，而进行的处理，使之可以正常的触发事务
 */
@Component
@Aspect
@Slf4j
public class SeataFeignCallBackAop {

    @Pointcut("@annotation(com.lipeng.common.SeataFeignCallBack)")
    public void operationCut() {
    }

    @Before("operationCut()")
    public void before(JoinPoint joinPoint) throws TransactionException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        SeataFeignCallBack annotation = signature.getMethod().getAnnotation(SeataFeignCallBack.class);
        Method method = signature.getMethod();
        log.info("拦截到需要分布式事务的方法:" + method.getName());
        // 此处可用redis或者定时任务来获取一个key判断是否需要关闭分布式事务
        GlobalTransaction tx = GlobalTransactionContext.getCurrentOrCreate();
        tx.begin(10000, annotation.transactionName());
        log.info("创建分布式事务完毕" + tx.getXid());
    }

    @AfterThrowing(throwing = "e", pointcut = "operationCut()")
    public void doRecoveryActions(Throwable e) throws TransactionException {
        log.info("方法执行异常:" + e.getMessage(), e);
        if (!StringUtils.isBlank(RootContext.getXID())) {
            GlobalTransactionContext.reload(RootContext.getXID()).rollback();
        }
    }

    @AfterReturning(pointcut = "operationCut()", returning = "result")
    public void afterReturning(JoinPoint point, Object result) throws TransactionException {
        if (!StringUtils.isBlank(RootContext.getXID())) {
            log.info("分布式事务Id:{}", RootContext.getXID());
            GlobalTransactionContext.reload(RootContext.getXID()).commit();
        }
    }

}