/*
 * Copyright (c) 2020 Wang Zemin, all rights reserved.
 */

package com.wangzemin.rpncalculate.utils;

/**
 * @author : Wang Zemin
 * @version : 1.0
 */
public interface CalculatorConst {

    /**
     * 数字的正则表达式, 目前仅仅支持小数和整数.
     */
    String NUMBER_PATTERN = "\\d+|\\d+\\.\\d+";

    /**
     * 计算器运行过程中的精度.
     */
    int DEFAULT_CALCULATE_SCALE = 15;

    /**
     * 给用户展示数字的精度.
     */
    int DEFAULT_DISPLAY_SCALE = 10;
}
