/*
 * Copyright (c) 2020 Wang Zemin, all rights reserved.
 */

package com.wangzemin.rpncalculate.operator;

/**
 * 计算器的操作, 目前包含计算操作{@link CalculateOperator}和全局操作{@link GlobalOperator}两种.
 *
 * @author : Wang Zemin
 * @version : 1.0
 */
public interface Operator {

    /**
     * 查询操作对应的操作符号。
     *
     * @return : 返回全局操作对应的操作符, 如"UNDO".
     */
    String getSymbolPattern();
}
