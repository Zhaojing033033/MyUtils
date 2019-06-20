package com.zj.util;

import java.math.BigDecimal;

public class MathUtil {
    /**
     * 除法
     * @param dividend 被除数
     * @param divisor 除数
     * @param retain 保留小数位数
     * @return
     */
    public static Double  divid(Double dividend,Double divisor,int retain){
        BigDecimal divide = new BigDecimal(dividend).divide(new BigDecimal(divisor), retain, BigDecimal.ROUND_HALF_UP);
        return divide.doubleValue();
    }
    /**
     * 除法（默认保留两位小数）
     * @param divisored 被除数
     * @param divisor 除数
     * @return
     */
    public static Double  divid(Double divisored,Double divisor){
        return divid(divisored,divisor,2);
    }

    /**
     * 减法之后，求绝对值
     * @param minuend 被减数
     * @param subtractor 减数
     * @return
     */
    public static Double subtractAndAbs(Double minuend,Double subtractor){
        BigDecimal subtract = new BigDecimal(minuend).subtract(new BigDecimal(subtractor));
        return Math.abs(subtract.doubleValue());
    }
}
