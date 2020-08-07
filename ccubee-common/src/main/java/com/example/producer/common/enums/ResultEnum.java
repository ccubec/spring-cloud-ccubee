package com.example.producer.common.enums;

import com.example.producer.common.exception.CubeException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author ccubee
 * @since 20-1-2 21:30
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {

    /**
     * 成功
     */
    SUCCESS(0, "成功"),
    /**
     * 失败
     */
    FAIL(1,"失败"),

    NOT_FOUND(404, "没有找到对应数据")
    ;

    private Integer code;

    private String msg;


    public static ResultEnum of(Integer code){
        Objects.requireNonNull(code);
        return Stream.of(values())
                .filter(bean -> bean.code.equals(code))
                .findAny()
                .orElseThrow(() -> new CubeException(ResultEnum.NOT_FOUND.code, ResultEnum.NOT_FOUND.msg));
    }

}
