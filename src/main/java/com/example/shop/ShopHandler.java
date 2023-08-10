package com.example.shop;

public class ShopHandler {

    public static boolean checkLength(String name, int count) {
        return name.length() >= count;
    }

    public static boolean checkFirstLetter(String name) {
        String firstLetter = name.substring(0, 1);
        return !firstLetter.equals(firstLetter.toLowerCase());
    }
}
