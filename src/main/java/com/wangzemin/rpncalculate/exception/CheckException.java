/*
 * Copyright (c) 2020 Wang Zemin, all rights reserved.
 */

package com.wangzemin.rpncalculate.exception;

/**
 * @author : Wang Zemin
 * @version : 1.0
 */
public class CheckException {
    public static class ParamNumException extends RuntimeException {
        public ParamNumException(int expect, int actual) {
            super(String.format("Wrong number of params, expect %d but find %d param.", expect, actual));
        }
    }
}
