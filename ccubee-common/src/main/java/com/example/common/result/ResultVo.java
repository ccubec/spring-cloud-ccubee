package com.example.common.result;

import com.example.common.enums.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ccubee
 * @since 20-1-2 21:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVo {

    private Integer code;

    private String msg;

    private Object data;

    public static ResultVo success(){
        return new ResultVo(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), null);
    }

    public static ResultVo success(Object data){
        return new ResultVo(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), data);
    }

    public static ResultVo fail(){
        return new ResultVo(ResultEnum.FAIL.getCode(), ResultEnum.FAIL.getMsg(), null);
    }

    public static ResultVo fail(String msg){
        return new ResultVo(ResultEnum.FAIL.getCode(), msg, null);
    }
}
