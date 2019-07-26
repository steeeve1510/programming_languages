package com.pl.calculator.operator;

public class CodeUtil {

    /**
     * b:  true (1) or false 0
     * l1: code to be executed if false
     * l2: code to be executed if true
     */
    public static String if_b_l1_l2() {
        return "(4!4$1+$@)@";
    }

    /**
     * l: this list will be looped forever
     */
    public static String loop_l() {
        return " (" +
                " (3!@2!@) " +
                "  3!@2!@  " +
                ")@";
    }

    /**
     * lb: a list that evaluates to a boolean
     * l1: this list will be looped
     */
    public static String while_lb_l1() {
        return " (" +
                " (4!@ (1$1$1$) (3!@2!@)" + if_b_l1_l2() + " )" +
                "  4!@ (1$1$1$) (3!@2!@)" + if_b_l1_l2() + "  " +
                ")@";
    }

    public static String dup() {
        return "2!";
    }

    public static String drop() {
        return "1$";
    }

    public static String printTopOfStack() {
        return " (\n)\"" +
                "(Top of Stack: )\" " + dup() + "\"" +
                "(\n)\"";
    }

    public static String clearInReverseExceptFirstTwo() {
        return " (" +
                "  (#5>) " +
                "  (# 2! ! \"( )\" 2-$) " +
                "  " + while_lb_l1() +
                ")@";
    }

    public static String welcome() {
        return "(Welcome to the Calculator!\n)\" " +   // print hello
                "(' " +                                // start of loop
                "   ? (@) ()" + if_b_l1_l2() +         // print the number or execute the list
                "   " + clearInReverseExceptFirstTwo() + // print the stack, except the last two elements
                "   (\n)\" " +                         // print newline
                ")" + loop_l();
    }
}
