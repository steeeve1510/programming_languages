package com.pl.calculator;

import com.pl.calculator.operator.Operator;

public class Calculator {

    public static void main(String[] args) {
        var operator = new Operator();
        operator.run();

//        primeFactor(7);
    }

    public static void primeFactor(int number) {
        while (number > 1) {
            int factor = findSmallestFactor(number);
            System.out.println("Found prime factor: " + factor);
            number = number / factor;
        }
    }

    public static int findSmallestFactor(int number) {
        int factorCandidate = 1;
        int rest = 1;
        while (rest != 0) {
            factorCandidate++;
            rest = number % factorCandidate;
        }
        return factorCandidate;
    }
}
