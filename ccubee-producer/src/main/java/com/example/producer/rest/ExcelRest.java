package com.example.producer.rest;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.example.producer.common.result.ResultVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author ccubee
 * @since 20-3-8 14:17
 */
@RestController
public class ExcelRest {

    @Value("${server.port}")
    String port;

    @PostMapping("uploadExcel")
    public ResultVo getExcel(@RequestParam("file") MultipartFile file) throws Exception{
        InputStream inputStream = file.getInputStream();
        ExcelReaderBuilder read = EasyExcel.read(inputStream);
        ReadSheet build = read.sheet(0).build();

        return ResultVo.success("输出");
    }


    @GetMapping("test")
    public ResultVo hello() throws Exception{
        System.out.println(port);

        return ResultVo.success(port);
    }


}
