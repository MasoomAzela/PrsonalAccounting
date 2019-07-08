package com.uni.pa.utility;

public class RandomUtil {

    public String randomGenerator(int count) {
        String randomResult;
        final String seed = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * seed.length());
            builder.append(seed.charAt(character));
        }
        return randomResult = builder.toString();
    }
}
