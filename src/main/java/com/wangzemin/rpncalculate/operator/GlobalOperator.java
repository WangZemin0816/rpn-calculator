/*
 * Copyright (c) 2020 Wang Zemin, all rights reserved.
 */

package com.wangzemin.rpncalculate.operator;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * 全局操作: 清除数据, UNDO等命令.
 *
 * @author : Wang Zemin
 * @version : 1.0
 */
public interface GlobalOperator extends Operator{

    /**
     * 根据当前波兰表达式的值和之前运行过的操作数的栈, 执行全局操作.
     *
     * @param currentRpnStack : 当前逆波兰表达式栈.
     * @param operatorHistory : 之前执行过的命令.
     */
    void operator(Stack<BigDecimal> currentRpnStack, Stack<String> operatorHistory);


}
