/*
 * Copyright (c) 2020 Wang Zemin, all rights reserved.
 */

package com.wangzemin.rpncalculate.exception;

/**
 * @author : Wang Zemin
 * @version : 1.0
 */
public class InputException {

    public static class UnknownOperatorException extends RuntimeException {
        public UnknownOperatorException(String operatorSymbol) {
            super(String.format("Find unknown operator[%s], Can not continue calculate.", operatorSymbol));
        }
    }

    public static class  InsufficientParamException extends RuntimeException {
        public InsufficientParamException(String operatorSymbol) {
            super(String.format("operator[%s]: insufficient parameters.", operatorSymbol));
        }
    }

    public static class  TooManyOperatorException extends RuntimeException {
        public TooManyOperatorException(String operatorSymbol) {
            super(String.format("Receive too many operators, can not execute {}..", operatorSymbol));
        }
    }
}
