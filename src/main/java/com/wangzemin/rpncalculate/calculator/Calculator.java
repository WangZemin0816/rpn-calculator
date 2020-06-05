/*
 * Copyright (c) 2020 Wang Zemin, all rights reserved.
 */

package com.wangzemin.rpncalculate.calculator;

import com.wangzemin.rpncalculate.utils.CalculatorConst;

import java.util.List;

/**
 * 计算器抽象接口.
 *
 * @author : Wang Zemin
 * @version : 1.0
 */
public interface Calculator extends CalculatorConst {

    /**
     * 对当前计算器添加用户的运算输入.
     */
    void calculate(String userInput);

    /**
     * 返回当前栈中得到数据, 以String(plain decimal)格式返回.
     */
    List<String> getRpnStackInfo();

}
