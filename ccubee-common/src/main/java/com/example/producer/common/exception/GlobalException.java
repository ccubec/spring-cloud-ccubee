package com.example.producer.common.exception;

import com.example.producer.common.result.ResultVo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author ccubee
 * @since 20-1-2 21:27
 */
@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(CubeException.class)
    public ResultVo exception(CubeException ex){
        return  ResultVo.fail(ex.getErrMsg());
    }


    @ExceptionHandler(Exception.class)
    public ResultVo exception(Exception ex){
        return  ResultVo.fail(ex.getMessage());
    }

}
