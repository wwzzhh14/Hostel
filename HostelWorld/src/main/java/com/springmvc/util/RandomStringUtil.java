package com.springmvc.util;

import java.util.Random;

/**
 * Created by wzh on 18/01/2017.
 */
public class RandomStringUtil {

    public static String getRandomNumberString(int length){
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

}
