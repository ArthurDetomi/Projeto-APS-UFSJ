package edu.ufsj.utils;

public class StringUtil {

    public static String keepOnlyNumbers(String str) {
        return str.replaceAll("[^0-9]", "");
    }

}
