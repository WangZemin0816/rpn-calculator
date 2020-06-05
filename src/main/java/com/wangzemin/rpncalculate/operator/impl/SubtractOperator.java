/*
 * Copyright (c) 2020 Wang Zemin, all rights reserved.
 */

package com.wangzemin.rpncalculate.operator.impl;

import com.wangzemin.rpncalculate.annotation.CheckOperatorParamNum;
import com.wangzemin.rpncalculate.operator.CalculateOperator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * 减法操作运算.
 *
 * @author : Wang Zemin
 * @version : 1.0
 */
@Slf4j
@Component("subtractOperator")
public class SubtractOperator implements CalculateOperator {
    /**
     * 减法操作需要两个运算符参与.
     */
    @Getter
    private final int paramNum = 2;

    /**
     * 减法操作的符号, 用于识别输入中是否包含减法.
     */
    @Getter
    private final String symbolPattern = "-";

    @Override
    @CheckOperatorParamNum
    public BigDecimal operator(List<BigDecimal> params, int scale) {
        BigDecimal firstParam = params.get(0);
        BigDecimal secondParam = params.get(1);
        BigDecimal res = firstParam.subtract(secondParam);
        // log.info("Do subtract operator with params :{} and {}, get res: {}", firstParam, secondParam, res);
        return res.setScale(scale, RoundingMode.HALF_EVEN);
    }
}
