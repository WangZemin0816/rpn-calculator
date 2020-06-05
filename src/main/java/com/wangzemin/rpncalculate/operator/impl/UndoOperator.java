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
import java.util.regex.Pattern;

/**
 * @author : Wang Zemin
 * @version : 1.0
 */
@Slf4j
@Component("undoOperator")
public class UndoOperator implements GlobalOperator, CalculatorConst {

    /**
     * 加法操作的符号, 用于识别输入中是否包含加法.
     */
    @Getter
    private final String symbolPattern = "(?i)undo";

    /**
     * 移除栈顶的数字(上一次的计算结果), 退回操作历史中的数字(第一个不为数字之前的所有数字)
     *
     * @param currentRpnStack : 当前逆波兰表达式栈.
     * @param operatorHistory : 之前执行过的命令.
     */
    @Override
    public void operator(Stack<BigDecimal> currentRpnStack, Stack<String> operatorHistory) {
        // 移除上一次运算的结果.
        if (!currentRpnStack.isEmpty()) {
            currentRpnStack.pop();
        }

        // 如果操作记录的头部为数字, 表示上一次操作历史中包含运算, 把参与运算的数字弹回逆波兰表达式栈中.
        while (!operatorHistory.isEmpty()) {
            String preElement = operatorHistory.pop();
            if (!Pattern.matches(NUMBER_PATTERN, preElement)) {
                break;
            }
            currentRpnStack.push(new BigDecimal(preElement));
        }
    }

}
