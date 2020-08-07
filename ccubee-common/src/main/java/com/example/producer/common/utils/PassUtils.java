package com.example.producer.common.utils;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * @author ccubee
 * @since 20-2-13 18:48
 */
public class PassUtils {

    static int[] numbers = {1,2,3,4,5,6,7,8,9,0};

    static String[] a = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};


    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream(new BufferedOutputStream(
                new FileOutputStream("H:\\pass\\outpass.txt")), true));


        double pow = Math.pow(10, 8);
        for(int j = 0 ; j < pow; j++ ) {
                   String random = UUIDUtils.getRandom(true, 8);
                   System.out.println(random);
               }



    }



}
