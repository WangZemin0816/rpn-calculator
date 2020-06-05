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
 * 除法操作.
 *
 * @author : Wang Zemin
 * @version : 1.0
 */
@Slf4j
@Component("divideOperator")
public class DivideOperator implements CalculateOperator {

    /**
     * 除法操作需要两个运算符参与.
     */
    @Getter
    private final int paramNum = 2;

    /**
     * 除法操作的符号, 用于识别输入中是否包含除法.
     */
    @Getter
    private final String symbolPattern = "/";

    @Override
    @CheckOperatorParamNum
    public BigDecimal operator(List<BigDecimal> params, int scale) {
        int calculateScale = scale + 1;
        BigDecimal firstParam = params.get(0).setScale(calculateScale, RoundingMode.HALF_EVEN);
        BigDecimal secondParam = params.get(1).setScale(calculateScale, RoundingMode.HALF_EVEN);
        if (secondParam.compareTo(BigDecimal.ZERO) == 0) {
            // log.error("Illegal divided num zero.");
            throw new IllegalArgumentException("Illegal divided num zero.");
        }
        BigDecimal res = firstParam.divide(secondParam, RoundingMode.HALF_EVEN);
        // log.info("Do divide operator with params :{} and {}, get res: {}", firstParam, secondParam, res);
        return res.setScale(scale, RoundingMode.HALF_EVEN);
    }
}
