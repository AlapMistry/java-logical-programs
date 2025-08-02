package com.example;

import java.math.BigDecimal;

public class BigDecimalMethods 
{
    public static void main( String[] args )
    {
        BigDecimal small = new BigDecimal(-1);
        BigDecimal equal = BigDecimal.ONE;
        BigDecimal big = BigDecimal.TEN;
        String great = "Great";
        String less = "Less";

        System.out.println("Small: " + (BigDecimal.ONE.compareTo(small) < 0 ? great : less));
        System.out.println("Equal: " + (BigDecimal.ONE.compareTo(equal) < 0 ? great : less));
        System.out.println("Big: " + (BigDecimal.ONE.compareTo(big) < 0 ? great : less));
        System.out.println("Subtract: " + (big.subtract(BigDecimal.ONE)));
    }
}
