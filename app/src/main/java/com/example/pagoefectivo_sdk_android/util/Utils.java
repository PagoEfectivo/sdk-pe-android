package com.example.pagoefectivo_sdk_android.util;

public class Utils {
    public static String addZeroToNumber(String str){
        String newStr = str;
        if(str.length()==1){
            newStr = "0" + str;
        }
        return newStr;
    }
}
