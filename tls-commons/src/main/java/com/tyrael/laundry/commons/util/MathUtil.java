package com.tyrael.laundry.commons.util;

import java.math.BigDecimal;

import com.google.common.base.Preconditions;

/**
 * 
 * @author Mark Martinez, create Feb 9, 2016
 *
 */
public class MathUtil {

    public static boolean gt(BigDecimal a, BigDecimal b) {
        Preconditions.checkNotNull(a);
        Preconditions.checkNotNull(b);
        return a.compareTo(b) > 0;
    }

    public static boolean gte(BigDecimal a, BigDecimal b) {
        Preconditions.checkNotNull(a);
        Preconditions.checkNotNull(b);
        return a.compareTo(b) >= 0;
    }

    public static boolean lt(BigDecimal a, BigDecimal b) {
        Preconditions.checkNotNull(a);
        Preconditions.checkNotNull(b);
        return a.compareTo(b) < 0;
    }

    public static boolean eq(BigDecimal arg0, BigDecimal arg1) {
        if (null == arg0 || null == arg1) {
            return false;
        }
        return arg0.compareTo(arg1) == 0;
    }

}
