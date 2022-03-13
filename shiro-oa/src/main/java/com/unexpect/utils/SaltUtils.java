package com.unexpect.utils;

import java.util.Random;

public class SaltUtils {

    public static String getSalt(int n){
        char[] chars = "ABCDEFGHIJKLNOPQRSTUVWXYZabcdefghijklnopqrstuvwxyz0123456789!@#$%^&*()".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char aChar = chars[new Random().nextInt(chars.length)];
            stringBuilder.append(aChar);
        }
        return  stringBuilder.toString();
    }

    public static void main(String[] args){
        String salt = getSalt(4);
        System.out.println(salt);
    }
}
