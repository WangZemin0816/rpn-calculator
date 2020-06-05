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
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

/**
 * 开方操作.
 *
 * @author : Wang Zemin
 * @version : 1.0
 */
@Slf4j
@Component("sqrtOperator")
public class SqrtOperator implements CalculateOperator {

    /**
     * 开方操作需要一个运算符参与.
     */
    @Getter
    private final int paramNum = 1;

    /**
     * 开方操作的符号, 用于识别输入中是否包含开方.
     */
    @Getter
    private final String symbolPattern = "(?i)sqrt";

    @Override
    @CheckOperatorParamNum
    public BigDecimal operator(List<BigDecimal> params, int scale) {
        int calculateScale = scale + 1;
        BigDecimal firstParam = params.get(0).setScale(calculateScale, RoundingMode.HALF_EVEN);
        BigDecimal res = firstParam.sqrt(new MathContext(calculateScale, RoundingMode.HALF_EVEN));
        // log.info("Do sqrt operator with params :{}, get res: {}", firstParam, res);
        return res.setScale(scale, RoundingMode.HALF_EVEN);
    }
}
