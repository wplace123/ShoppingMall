package com.example.shopping.mall.common;

/**
 * 
 * @author ryan wang
 * @date 2018/12/02
 */
public class Utils {
    public static boolean isPositiveInteger(Integer number) {
        if (number != null && number > 0) {
            return true;
        }
        return false;
    }
}
