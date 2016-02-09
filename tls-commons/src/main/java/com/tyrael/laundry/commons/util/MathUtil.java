package com.tyrael.laundry.commons.util;

import java.math.BigDecimal;

/**
 * 
 * @author Mark Martinez, create Feb 9, 2016
 *
 */
public class MathUtil {

    public static boolean gt(BigDecimal a, BigDecimal b) {
        return null != a && a.compareTo(b) > 0;
    }

    public static boolean gte(BigDecimal a, BigDecimal b) {
        return null != a && a.compareTo(b) >= 0;
    }

}
