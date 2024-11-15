package edu.ufsj.utils;

public class StringUtil {

    public static String keepOnlyNumbers(String str) {
        if (str == null) return null;

        return str.replaceAll("[^0-9]", "");
    }

}
