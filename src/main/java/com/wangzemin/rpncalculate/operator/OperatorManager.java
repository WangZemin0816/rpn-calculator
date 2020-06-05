/*
 * Copyright (c) 2020 Wang Zemin, all rights reserved.
 */

package com.wangzemin.rpncalculate.operator;

import com.wangzemin.rpncalculate.exception.InputException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.regex.Pattern;

/**
 * 用于管理各种计算操作和全局操作.
 *
 * @author : Wang Zemin
 * @version : 1.0
 */
@Slf4j
@Service
public class OperatorManager {

    /**
     * 存放计算操作的SymbolPattern和计算操作Bean对应关系的Map.
     */
    private final Map<String, Operator> operatorMap = new HashMap<>();

    /**
     * 读写锁, 保证对{@link this#operatorMap}操作的原子性.
     */
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /**
     * 依赖Spring自动注入获取计算操作和全局操作实体, 初始化Map.
     *
     * @param allOperators : Spring发现的所有操作列表.
     */
    public OperatorManager(List<Operator> allOperators) {
        for (Operator operator : allOperators) {
            addOperator(operator);
        }
    }

    /**
     * 根据用户的输入获取对应的运算操作.
     *
     * @param symbol : 用户的输入.
     * @return : 操作，包含计算操作和全局操作两种.
     */
    public Operator getOperatorBySymbol(String symbol) {
        readWriteLock.readLock().lock();
        try {
            for (String operatorSymbol : operatorMap.keySet()) {
                if (Pattern.matches(operatorSymbol, symbol)) {
                    return operatorMap.get(operatorSymbol);
                }
            }
        } finally {
            readWriteLock.readLock().unlock();
        }
        log.error("Find unknown operator[{}}], Can not continue calculate.", symbol);
        throw new InputException.UnknownOperatorException(symbol);
    }

    /**
     * 向{@link this#operatorMap}中添加一个Operator。
     *
     * @param operator : 需要添加的Operator。
     */
    public void addOperator(Operator operator) {
        readWriteLock.writeLock().lock();
        try {
            operatorMap.put(operator.getSymbolPattern(), operator);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
}
