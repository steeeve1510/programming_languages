package com.pl.calculator;

import com.pl.calculator.operator.Operator;

public class Calculator {

    public static void main(String[] args) {
        boolean stop = false;
        while (!stop) {
            try {
                Operator operator = new Operator();
                operator.run();
                stop = true;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Restarting...");
            }
        }
    }
}
