/*
 * Copyright (c) 2020 Wang Zemin, all rights reserved.
 */

package com.wangzemin.rpncalculate.calculator;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * 由于涉及到多线程, 此处仅仅做简单测试, 测试最终结果是否符合预期.
 *
 * @author : Wang Zemin
 * @version : 1.0
 */
@Slf4j
@SpringBootTest
public class RPNCalculatorTest {

    @Autowired
    private Calculator rpnCalculator;

    @Test
    @SneakyThrows
    public void simpleTest() {

        String input1 = "1 2 3";
        rpnCalculator.calculate(input1);
        // 测试数字是否可以正常入栈
        List<String> res1 = rpnCalculator.getRpnStackInfo();
        assertEquals(res1.size(), 3);
        assertEquals("1", res1.get(0));
        assertEquals("2", res1.get(1));
        assertEquals("3", res1.get(2));

        // 测试运算符表现
        rpnCalculator.calculate("+");
        List<String> res2 = rpnCalculator.getRpnStackInfo();
        assertEquals(res2.size(), 2);
        assertEquals("1", res1.get(0));
        assertEquals("5", res1.get(1));

        // 测试两个运算符

        rpnCalculator.calculate("CLEAr");
        List<String> res3 = rpnCalculator.getRpnStackInfo();
        assertEquals(res3.size(), 0);

    }

    @Test
    public void caseOperatorTest() {
        // Example 1
        rpnCalculator.calculate("5 2");
        List<String> res1 = rpnCalculator.getRpnStackInfo();
        assertEquals(res1.size(), 2);
        assertEquals("5", res1.get(0));
        assertEquals("2", res1.get(1));
        rpnCalculator.calculate("clear");

        // Example 2
        rpnCalculator.calculate("2 sqrt");
        List<String> res2 = rpnCalculator.getRpnStackInfo();
        assertEquals(res2.size(), 1);
        assertEquals("1.4142135623", res2.get(0));
        rpnCalculator.calculate("clear 9 sqrt");
        List<String> res3 = rpnCalculator.getRpnStackInfo();
        assertEquals(res3.size(), 1);
        assertEquals("3", res3.get(0));
        rpnCalculator.calculate("clear");

        // Example 3
        rpnCalculator.calculate("5 2 -");
        List<String> res4 = rpnCalculator.getRpnStackInfo();
        assertEquals(res4.size(), 1);
        assertEquals("3", res4.get(0));
        rpnCalculator.calculate("clear");

        // Example 4
        rpnCalculator.calculate("5 4 3 2 undo undo *");
        List<String> res5 = rpnCalculator.getRpnStackInfo();
        assertEquals(res5.size(), 1);
        assertEquals("20", res5.get(0));
        rpnCalculator.calculate("5 *");
        List<String> res6 = rpnCalculator.getRpnStackInfo();
        assertEquals(res6.size(), 1);
        assertEquals("100", res6.get(0));
        rpnCalculator.calculate("undo");
        List<String> res7 = rpnCalculator.getRpnStackInfo();
        assertEquals(res7.size(), 2);
        assertEquals("20", res7.get(0));
        assertEquals("5", res7.get(1));
        rpnCalculator.calculate("clear");

        // Example 5
        rpnCalculator.calculate("7 12 2 /");
        List<String> res8 = rpnCalculator.getRpnStackInfo();
        assertEquals(res8.size(), 2);
        assertEquals("7", res8.get(0));
        assertEquals("6", res8.get(1));
        rpnCalculator.calculate("*");
        List<String> res9 = rpnCalculator.getRpnStackInfo();
        assertEquals(res9.size(), 1);
        assertEquals("42", res9.get(0));
        rpnCalculator.calculate("4 /");
        List<String> res10 = rpnCalculator.getRpnStackInfo();
        assertEquals(res10.size(), 1);
        assertEquals("10.5", res10.get(0));
        rpnCalculator.calculate("clear");

        // Example 6
        rpnCalculator.calculate("1 2 3 4 5");
        List<String> res11 = rpnCalculator.getRpnStackInfo();
        assertEquals(res11.size(), 5);
        rpnCalculator.calculate("*");
        List<String> res12 = rpnCalculator.getRpnStackInfo();
        assertEquals(res12.size(), 4);
        rpnCalculator.calculate("clear 3 4 -");
        List<String> res13 = rpnCalculator.getRpnStackInfo();
        assertEquals(res13.size(), 1);
        assertEquals("-1", res13.get(0));
        rpnCalculator.calculate("clear");

        // Example 7
        rpnCalculator.calculate("1 2 3 4 5");
        List<String> res14 = rpnCalculator.getRpnStackInfo();
        assertEquals(res14.size(), 5);
        rpnCalculator.calculate("* * * *");
        List<String> res15 = rpnCalculator.getRpnStackInfo();
        assertEquals(res15.size(), 1);
        assertEquals("120", res15.get(0));
        rpnCalculator.calculate("clear");

        // Example 8
        rpnCalculator.calculate("1 2 3 * 5 + * * 6 5");

    }

}