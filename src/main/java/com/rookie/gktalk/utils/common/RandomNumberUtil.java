package com.rookie.gktalk.utils.common;

import java.util.Random;

public class RandomNumberUtil {
    public static String getRandomNumber(int length){
        Random random = new Random();
        String num = "";

        for(int i = 0; i < length ; i++){
           num+=String.valueOf(random.nextInt(9));
        }

        return num;
    }
}
