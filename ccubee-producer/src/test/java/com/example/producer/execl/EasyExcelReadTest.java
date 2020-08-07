package com.example.producer.execl;


import com.example.producer.CcubeeProducerApplicationTests;
import org.apache.commons.math.stat.inference.TestUtils;
import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * @author ccubee
 * @since 20-3-8 14:27
 */
public class EasyExcelReadTest extends CcubeeProducerApplicationTests {

    @Test
    void read(){
        System.out.println(TestUtils.class.getResource("/"));
        File file = new File("F:\\java\\jar\\app\\spring-cloud-ccubee\\ccubee-producer\\src\\test\\resources\\demo\\demo.xlsx");

    }
}
