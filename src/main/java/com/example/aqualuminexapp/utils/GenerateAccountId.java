package com.example.aqualuminexapp.utils;

import java.util.Random;

public class GenerateAccountId {
    private static final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";


    private static String randomizeCharacters() {
        int index = Integer.parseInt(randomizeNumbers(0, characters.length()));
        int index2 = Integer.parseInt(randomizeNumbers(0, characters.length()));

        return characters.charAt(index) + String.valueOf(characters.charAt(index2));
    }

    private static String randomizeNumbers(int minValue, int maxValue) {
        Random acc_id = new Random();
        return String.valueOf(acc_id.nextInt(maxValue - minValue + 1) + minValue);
    }

    public String generateAccountId() {
        return randomizeCharacters() + "-" + randomizeNumbers(111111, 999999);
    }

    public String generateOTP(){
        return randomizeNumbers(1111, 9999);
    }
}