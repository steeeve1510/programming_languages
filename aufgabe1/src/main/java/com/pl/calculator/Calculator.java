package com.pl.calculator;

import com.pl.calculator.operator.Operator;

public class Calculator {
    public static void main(String[] args) {
//        String welcome = "(Welcome to the Calculator!)\"\'";
//        String welcome = "(2 2+\")@";
//        String welcome = "3(A)3!3$3!@2$";
//        String welcome = "(2 2+\")\\";
//        String welcome = "0(9~)(8)(4!4$1+$@)@";

//        String welcome = "3(3!3!1-2!1=4!()(4!4$1+$@)@2$*)3!3$3!@2$";

        String welcome = "3 2 1.5 5";



        var operator = new Operator(welcome);
        operator.run();
    }
}
