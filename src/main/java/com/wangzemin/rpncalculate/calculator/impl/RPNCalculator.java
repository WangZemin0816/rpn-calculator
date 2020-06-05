/*
 * Copyright (c) 2020 Wang Zemin, all rights reserved.
 */

package com.wangzemin.rpncalculate.calculator.impl;

import com.wangzemin.rpncalculate.calculator.Calculator;
import com.wangzemin.rpncalculate.exception.InputException;
import com.wangzemin.rpncalculate.operator.CalculateOperator;
import com.wangzemin.rpncalculate.operator.GlobalOperator;
import com.wangzemin.rpncalculate.operator.Operator;
import com.wangzemin.rpncalculate.operator.OperatorManager;
import com.wangzemin.rpncalculate.utils.CalculatorConst;
import com.wangzemin.rpncalculate.utils.InputParse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * @author : Wang Zemin
 * @version : 1.0
 */
@Slf4j
@Component
public class RPNCalculator implements Calculator, CalculatorConst {

    /**
     * 逆波兰表达式栈.
     */
    private final Stack<BigDecimal> rpnCalculateStack = new Stack<>();

    /**
     * 操作历史.
     */
    private final Stack<String> operatedHistory = new Stack<>();

    /**
     * 管理各种操作的服务.
     */
    private final OperatorManager operatorManager;

    /**
     * 是否已经因为异常被中止.
     */
    private boolean isTerminated = false;


    @Autowired
    public RPNCalculator(OperatorManager operatorManager) {
        this.operatorManager = operatorManager;
    }


    @Override
    public void calculate(String userInput) {
        if (isTerminated) {
            log.error("The calculate has been terminated, can not continue calculate.");
            return;
        }
        int pos = 1;
        String currentElement = "";
        try {
            List<String> elements = InputParse.splitInput(userInput);
            for (String element : elements) {
                currentElement = element;
                calculateOneElement(element);
                pos = pos + element.length() + 1;
            }
        } catch (Exception e) {
            isTerminated = true;
            log.error("operator {} (position: {}): insucient parameters", currentElement, pos);

        }
        log.info(printCurrentStack());
    }

    /**
     * 按照指定精度展示栈中的数字信息.
     *
     * @return 逆波兰计算栈中的数字信息.
     */
    @Override
    public List<String> getRpnStackInfo() {
        BigDecimal[] bigDecimals = new BigDecimal[rpnCalculateStack.size()];
        rpnCalculateStack.toArray(bigDecimals);
        List<String> plainDecimals = new ArrayList<>();
        for (BigDecimal bigDecimal : bigDecimals) {
            BigDecimal scaledDecimal = bigDecimal.setScale(DEFAULT_DISPLAY_SCALE, RoundingMode.DOWN);
            plainDecimals.add(scaledDecimal.stripTrailingZeros().toPlainString());
        }
        return plainDecimals;
    }


    /**
     * 执行一个操作或者操作数.
     *
     * @param element : 需要操作的元素.
     */
    public void calculateOneElement(String element) {
        // 数字直接压入rpn栈.
        if (Pattern.matches(NUMBER_PATTERN, element)) {
            rpnCalculateStack.push(new BigDecimal(element));
        }

        // 执行计算操作, 数字也有对应的操作.
        Operator operator = operatorManager.getOperatorBySymbol(element);
        if (operator instanceof CalculateOperator) {
            CalculateOperator calculateOperator = (CalculateOperator) operator;
            doCalculateOperator(calculateOperator);
        }
        // 执行全局操作, 比如undo.
        if (operator instanceof GlobalOperator) {
            GlobalOperator globalOperator = (GlobalOperator) operator;
            globalOperator.operator(rpnCalculateStack, operatedHistory);
        }
    }

    /**
     * 执行计算操作, 主要包含记录历史, 弹出操作数, 压入计算结果等操作.
     *
     * @param calculateOperator : 需要执行的操作.
     */
    private void doCalculateOperator(CalculateOperator calculateOperator) {
        List<BigDecimal> operateParams = new LinkedList<>();
        operatedHistory.push(calculateOperator.getSymbolPattern());
        if (rpnCalculateStack.size()< calculateOperator.getParamNum()) {
            // log.error("operator[{}]: insufficient parameters.", calculateOperator.getSymbolPattern());
            throw new InputException.InsufficientParamException(calculateOperator.getSymbolPattern());
        }
        for (int i = 0; i < calculateOperator.getParamNum(); i++) {
            BigDecimal operateParam = rpnCalculateStack.pop();
            operateParams.add(0, operateParam);
            operatedHistory.push(operateParam.toString());
        }
        BigDecimal calculateRes = calculateOperator.operator(operateParams);
        if (calculateRes != null) {
            rpnCalculateStack.push(calculateRes);
        }
    }

    private String printCurrentStack() {
        List<String> stackInfos = getRpnStackInfo();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("stack: ");
        String prefix = "";
        for (String stackInfo : stackInfos) {
            stringBuilder.append(prefix).append(stackInfo);
            prefix = " ";
        }
        return stringBuilder.toString();
    }


}
