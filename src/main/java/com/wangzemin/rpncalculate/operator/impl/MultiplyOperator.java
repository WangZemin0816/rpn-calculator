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
 * 乘法操作.
 *
 * @author : Wang Zemin
 * @version : 1.0
 */
@Slf4j
@Component("multiplyOperator")
public class MultiplyOperator implements CalculateOperator {

    /**
     * 乘法操作需要两个运算符参与.
     */
    @Getter
    private final int paramNum = 2;

    /**
     * 乘法操作的符号, 用于识别输入中是否包含乘法.
     */
    @Getter
    private final String symbolPattern = "\\*";

    @Override
    @CheckOperatorParamNum
    public BigDecimal operator(List<BigDecimal> params, int scale) {
        BigDecimal firstParam = params.get(0);
        BigDecimal secondParam = params.get(1);
        BigDecimal res = firstParam.multiply(secondParam);
        // log.info("Do multiply operator with params :{} and {}, get res: {}", firstParam, secondParam, res);
        return res.setScale(scale, RoundingMode.HALF_EVEN);
    }
}
