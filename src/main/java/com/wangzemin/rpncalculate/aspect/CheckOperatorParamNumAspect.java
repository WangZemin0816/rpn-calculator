/*
 * Copyright (c) 2020 Wang Zemin, all rights reserved.
 */

package com.wangzemin.rpncalculate.aspect;

import com.wangzemin.rpncalculate.operator.CalculateOperator;
import com.wangzemin.rpncalculate.exception.CheckException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用于检查带有{@link com.wangzemin.rpncalculate.annotation.CheckOperatorParamNum}标记的方法参数数目是否正确.
 *
 * @author : Wang Zemin
 * @version : 1.0
 */
@Slf4j
@Aspect
@Component
public class CheckOperatorParamNumAspect {

    /**
     * 定义切入点.
     */
    @Pointcut(value = "@annotation(com.wangzemin.rpncalculate.annotation.CheckOperatorParamNum)")
    public void checkParamPointCut() {
    }

    /**
     * 调用计算操作的相关方法前检查参数的数目.
     */
    @Before("checkParamPointCut()")
    public void doCheckParamNum(JoinPoint joinPoint) {
        Object target = joinPoint.getTarget();
        // 只处理运算操作相关的方法.
        if (!(target instanceof CalculateOperator)) {
            return;
        }
        CalculateOperator calculateOperator = (CalculateOperator) target;
        int expectParamNum = calculateOperator.getParamNum();

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if ((arg instanceof List)) {
                @SuppressWarnings("unchecked")
                List<Object> params = (List<Object>) arg;
                if (expectParamNum != params.size()) {
                    log.error("Find wrong num of params[expect={},actual={}] at {}.",
                            expectParamNum, params.size(), joinPoint.toShortString());
                    throw new CheckException.ParamNumException(expectParamNum, params.size());
                }
            }
        }
    }
}
