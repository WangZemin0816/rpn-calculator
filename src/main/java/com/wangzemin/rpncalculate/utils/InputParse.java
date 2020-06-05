/*
 * Copyright (c) 2020 Wang Zemin, all rights reserved.
 */

package com.wangzemin.rpncalculate.utils;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 把用户输入按照空格分隔为多个元素.
 *
 * @author : Wang Zemin
 * @version : 1.0
 */
public class InputParse {

    private final static String DELIMITER = " ";

    public static List<String> splitInput(String userInput) {
        if (StringUtils.isEmpty(userInput.trim())) {
            return new ArrayList<>();
        }
        String[] userInputs = userInput.split(DELIMITER);
        List<String> splitInput = new ArrayList<>();
        for (String oneInput : userInputs) {
            if (!StringUtils.isEmpty(oneInput.trim())) {
                splitInput.add(oneInput.trim());
            }
        }
        return splitInput;
    }
}
