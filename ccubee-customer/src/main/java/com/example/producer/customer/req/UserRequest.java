package com.example.producer.customer.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author zongk
 * @since 19-12-10 22:29
 */
@Data
public class UserRequest {

    @NotBlank(message = "用户名不能为空")
    private String user;

    @NotNull(message = "年龄不能为空")
    private Integer age;

    private String password;
}
