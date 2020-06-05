/*
 * Copyright (c) 2020 Wang Zemin, all rights reserved.
 */

package com.wangzemin.rpncalculate.operator.impl;

import com.wangzemin.rpncalculate.operator.GlobalOperator;
import com.wangzemin.rpncalculate.utils.CalculatorConst;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * 清除操作.
 *
 * @author : Wang Zemin
 * @version : 1.0
 */
@Slf4j
@Component("clearOperator")
public class ClearOperator implements GlobalOperator, CalculatorConst {
    /**
     * 加法操作的符号, 用于识别输入中是否包含加法.
     */
    @Getter
    private final String symbolPattern = "(?i)clear";

    @Override
    public void operator(Stack<BigDecimal> currentRpnStack, Stack<String> operatorHistory) {
        // 移除上一次运算的结果.
        currentRpnStack.clear();
        operatorHistory.clear();
    }
}
