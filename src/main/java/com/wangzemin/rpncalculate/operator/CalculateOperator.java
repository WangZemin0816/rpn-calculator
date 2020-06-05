/*
 * Copyright (c) 2020 Wang Zemin, all rights reserved.
 */

package com.wangzemin.rpncalculate.operator;

import com.wangzemin.rpncalculate.annotation.CheckOperatorParamNum;
import com.wangzemin.rpncalculate.utils.CalculatorConst;

import java.math.BigDecimal;
import java.util.List;

/**
 * 计算操作的抽象接口, 包含计算过程, 参数数目, 操作符类型等.
 *
 * @author : Wang Zemin
 * @version : 1.0
 */
public interface CalculateOperator extends Operator, CalculatorConst {

    /**
     * 根据给定参数, 计算运算的结果, 比如对于加法, 返回a+b的值.
     *
     * @param params : 参与运算的参数.
     * @return : 默认精度{@link this#DEFAULT_CALCULATE_SCALE}的运算结果.
     */
    @CheckOperatorParamNum
    default BigDecimal operator(List<BigDecimal> params) {
        return operator(params, DEFAULT_CALCULATE_SCALE);
    }


    /**
     * 根据给定参数, 计算运算的结果, 比如对于加法, 返回a+b的值.
     *
     * @param params : 参与运算的参数.
     * @param scale  : 小数点后保留几位数字.
     * @return : 指定精度的运算结果.
     */
    @CheckOperatorParamNum
    BigDecimal operator(List<BigDecimal> params, int scale);

    /**
     * 计算操作需要几个参数参与运算。
     *
     * @return : 计算操作需要的参数数目.
     */
    int getParamNum();

}
