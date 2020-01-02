package com.example.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author ccubee
 * @since 20-1-2 21:24
 */
@Data
@AllArgsConstructor
public class CubeException extends RuntimeException {

    private Integer errCode;

    private String errMsg;


    public CubeException( String errMsg) {
        super(errMsg);
    }
}
