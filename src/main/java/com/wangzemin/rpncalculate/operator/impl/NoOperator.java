/*
 * Copyright (c) 2020 Wang Zemin, all rights reserved.
 */

package com.wangzemin.rpncalculate.operator.impl;

import com.wangzemin.rpncalculate.operator.CalculateOperator;
import com.wangzemin.rpncalculate.utils.CalculatorConst;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * 无操作, 比如向逆波兰栈中压入一个数字, 并没有运算操作发生.
 *
 * @author : Wang Zemin
 * @version : 1.0
 */
@Slf4j
@Component("noOperator")
public class NoOperator implements CalculateOperator, CalculatorConst {


    /**
     * 无操作运算不需要参数.
     */
    @Getter
    private final int paramNum = 0;

    /**
     * 无操作运算适用于所有的数字情况.
     */
    @Getter
    private final String symbolPattern = NUMBER_PATTERN;


    /**
     * 无操作运算不返回运算结果, 所以最后一个入栈的数字会被当作无操作运算的结果(无中生有).
     */
    @Override
    public BigDecimal operator(List<BigDecimal> params, int scale) {
        return null;
    }

}
