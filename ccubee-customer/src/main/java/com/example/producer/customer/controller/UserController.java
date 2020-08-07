package com.example.producer.customer.controller;

import com.example.producer.common.result.ResultVo;
import com.example.producer.customer.req.UserRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author ccubee
 * @since 20-1-14 22:14
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${server.port}")
    String port;

    @PostMapping("/login")
    public ResultVo login(@Valid @RequestBody UserRequest userRequest, BindingResult bindingResult){
//        if(bindingResult.hasErrors()){
//            throw new CubeException(100, CommonUtils.getErrorMsg(bindingResult));
//        }
        return ResultVo.success(port);

    }
}
