/*
 * Copyright (c) 2020 Wang Zemin, all rights reserved.
 */

package com.wangzemin.rpncalculate.operator.impl;

import com.wangzemin.rpncalculate.operator.GlobalOperator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Stack;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Wang Zemin
 * @version : 1.0
 */
@SpringBootTest
public class ClearOperatorTest {

    @Autowired
    @Qualifier("clearOperator")
    private GlobalOperator globalOperator;


    @Test
    public void operatorTest() {
        // 上一次执行完操作之后, 结果为1 5
        Stack<BigDecimal> rpnStack = new Stack<>();
        rpnStack.push(new BigDecimal("1"));
        rpnStack.push(new BigDecimal("5"));

        // 上一次执行的操作时2+3
        Stack<String> opHistory = new Stack<>();
        opHistory.push("+");
        opHistory.push("3");
        opHistory.push("2");

        globalOperator.operator(rpnStack, opHistory);

        // undo 上一次操作之后
        assertEquals(0,rpnStack.size());
        assertEquals(0,opHistory.size());
    }
}