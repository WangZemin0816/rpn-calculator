/*
 * Copyright (c) 2020 Wang Zemin, all rights reserved.
 */

package com.wangzemin.rpncalculate.operator.impl;

import com.wangzemin.rpncalculate.exception.CheckException;
import com.wangzemin.rpncalculate.operator.CalculateOperator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Wang Zemin
 * @version : 1.0
 */
@SpringBootTest
public class DivideOperatorTest {


    @Autowired
    @Qualifier("divideOperator")
    private CalculateOperator calculateOperator;


    @Test
    void operatorTest() {
        List<BigDecimal> params = new ArrayList<>();
        params.add(new BigDecimal("1"));
        params.add(new BigDecimal("3"));
        BigDecimal res = calculateOperator.operator(params);
        assertEquals(0, res.compareTo(new BigDecimal("0.333333333333333")));
    }

    @Test
    void operatorWithScaleTest() {
        List<BigDecimal> params = new ArrayList<>();
        params.add(new BigDecimal("1"));
        params.add(new BigDecimal("3"));
        BigDecimal res = calculateOperator.operator(params, 8);
        assertEquals(0, res.compareTo(new BigDecimal("0.33333333")));
    }

    @Test
    void operatorWithZeroTest() {
        List<BigDecimal> params = new ArrayList<>();
        params.add(new BigDecimal("1"));
        params.add(new BigDecimal("0"));
        try {
            BigDecimal res = calculateOperator.operator(params, 8);
            fail();
        } catch (IllegalArgumentException ignored) {

        }
    }

    @Test
    void operatorWithWrongNumOfParamTest() {
        List<BigDecimal> params = new ArrayList<>();
        params.add(new BigDecimal("0.11111111111111111"));
        params.add(new BigDecimal("0.22222222222222222"));
        params.add(new BigDecimal("0.22222222222222222"));
        try {
            BigDecimal res = calculateOperator.operator(params, 8);
            fail();
        } catch (CheckException.ParamNumException ignored) {

        }
    }
}