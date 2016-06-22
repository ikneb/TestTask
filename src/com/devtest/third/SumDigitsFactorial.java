package com.devtest.third;

import java.math.BigInteger;

/**
 * Created by Eduard on 19.06.2016.
 */
public class SumDigitsFactorial {

    public static void main(String[] args) {
        int n = 100;
        SumDigitsFactorial sumDigitsFactorial = new SumDigitsFactorial();
        System.out.println(sumDigitsFactorial.sum(sumDigitsFactorial.fact(n)));
    }


    private BigInteger fact(int n) {
        //Creating variable BigInteger type for our result and initializing it
        BigInteger result = BigInteger.valueOf(1);
        for (int i = 1; i < n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    //Method for calculation result test
    private int sum(BigInteger n) {
        //Converts BigInteger in String
        String s = "" + n;
        int result = 0;
        //Converts this string to a character array
        char[] arr = s.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            //Converts character in type int
            int j = arr[i] - '0';
            result += j;

        }
        return result;
    }
}