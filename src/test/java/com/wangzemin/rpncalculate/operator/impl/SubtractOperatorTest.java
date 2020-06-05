/*
 * Copyright (c) 2020 Wang Zemin, all rights reserved.
 */

package com.wangzemin.rpncalculate.operator.impl;

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
public class SubtractOperatorTest {

    @Autowired
    @Qualifier("subtractOperator")
    private CalculateOperator calculateOperator;

    @Test
    void operatorTest() {
        List<BigDecimal> params = new ArrayList<>();
        params.add(new BigDecimal("5.111111111111111111111111111"));
        params.add(new BigDecimal("5"));
        BigDecimal res = calculateOperator.operator(params);
        assertEquals(0, res.compareTo(new BigDecimal("0.111111111111111")));
    }

    @Test
    void operatorWithScaleTest() {
        List<BigDecimal> params = new ArrayList<>();
        params.add(new BigDecimal("5.111111111111111111111111111"));
        params.add(new BigDecimal("5"));
        BigDecimal res = calculateOperator.operator(params,2);
        assertEquals(0, res.compareTo(new BigDecimal("0.11")));
    }
}