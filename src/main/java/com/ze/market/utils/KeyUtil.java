package com.ze.market.utils;

import java.util.Random;

/**
 * @program: market
 * @description: create random keys (currentTime + random size 9)
 * @author: Ze QIN
 * @create: 2020-05-24 11:37
 **/
public class KeyUtil {

    public static synchronized String createUniqueKey() {

        Random random = new Random();

        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }
}
