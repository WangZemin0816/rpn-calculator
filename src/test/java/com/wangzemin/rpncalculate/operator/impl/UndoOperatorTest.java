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

/**
 * @author : Wang Zemin
 * @version : 1.0
 */
@SpringBootTest
public class UndoOperatorTest {
    @Autowired
    @Qualifier("undoOperator")
    private GlobalOperator globalOperator;

    @Test
    void operatorTest() {
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
        BigDecimal[] rpnRes = new BigDecimal[rpnStack.size()];
        rpnStack.toArray(rpnRes);
        assertEquals(3,rpnRes.length);
        assertEquals(0,new BigDecimal("1").compareTo(rpnRes[0]));
        assertEquals(0,new BigDecimal("2").compareTo(rpnRes[1]));
        assertEquals(0,new BigDecimal("3").compareTo(rpnRes[2]));
     }
}