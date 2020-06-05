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
 * 加法操作.
 *
 * @author : Wang Zemin
 * @version : 1.0
 */
@Slf4j
@Component("addOperator")
public class AddOperator implements CalculateOperator {

    /**
     * 加法操作需要两个运算符参与.
     */
    @Getter
    private final int paramNum = 2;

    /**
     * 加法操作的符号, 用于识别输入中是否包含加法.
     */
    @Getter
    private final String symbolPattern = "\\+";

    @Override
    @CheckOperatorParamNum
    public BigDecimal operator(List<BigDecimal> params, int scale) {
        BigDecimal firstParam = params.get(0);
        BigDecimal secondParam = params.get(1);
        BigDecimal sum = firstParam.add(secondParam);
        // log.info("Do add operator with params :{} and {}, get sum: {}", firstParam, secondParam, sum);
        return sum.setScale(scale, RoundingMode.HALF_EVEN);
    }

}
