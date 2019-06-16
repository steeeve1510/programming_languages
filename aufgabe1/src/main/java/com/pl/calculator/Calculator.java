package com.pl.calculator;

import com.pl.calculator.operator.Operator;

public class Calculator {
    public static void main(String[] args) {
//        String welcome = "(Welcome to the Calculator!)\"\'";
//        String welcome = "(2 2+\")@";
        String welcome = "(A)3!3$3!@2$";

        var operator = new Operator(welcome);
        operator.run();
    }
}
