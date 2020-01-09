package com.example.common.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.stream.Collectors;

/**
 *  参数校验 错误信息工具类
 * @author ccubee
 * @since 20-1-9 20:12
 */
public class CommonUtils {

    public static String getErrorMsg(BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            return "";
        }
        return bindingResult.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(","));
    }
}
