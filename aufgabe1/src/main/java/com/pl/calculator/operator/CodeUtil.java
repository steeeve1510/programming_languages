package com.pl.calculator.operator;

public class CodeUtil {

    /**
     * b:  true (1) or false 0
     * l1: code to be executed if false
     * l2: code to be executed if true
     */
    public static String if_b_l1_l2__() {
        return "(4!4$1+$@)@";
    }

    /**
     * l: this list will be looped forever
     */
    public static String loop_l__() {
        return " (" +
                " (3!@2!@) " +
                "  3!@2!@  " +
                ")@";
    }

    /**
     * lb: a list that evaluates to a boolean
     * l1: this list will be looped
     */
    public static String while_lb_l1__() {
        return " (" +
                " (4!@ (1$1$1$) (3!@2!@)" + if_b_l1_l2__() + " )" +
                "  4!@ (1$1$1$) (3!@2!@)" + if_b_l1_l2__() + "  " +
                ")@";
    }

    public static String dup_x__x_x() {
        return "2!";
    }

    public static String drop_x__() {
        return "1$";
    }

    public static String swap_x1_x2__x2_x1() {
        return "3!3$";
    }

    public static String printTopOfStack_x__x() {
        return " (\n)\"" +
                "(Top of Stack: )\" " + dup_x__x_x() + "\"" +
                "(\n)\"";
    }

    /**
     * int findSmallestFactor(int number) {
     *     int factorCandidate = 1;
     *     int rest = 1;
     *     while (rest != 0) {
     *         factorCandidate++;
     *         rest = number % factorCandidate;
     *     }
     *     return factorCandidate;
     * }
     */
    public static String getSmallestFactor_n__n() {
        return " (" +
                "  1Y 1X " +            // initialize
                "  (x 0 >)" +           // while condition
                "  (5! y1+ 2! Y % X)" + // while body
                "  " + while_lb_l1__() +
                "  1$ y" +              // cleanup
                ")@";
    }

    public static String clearInReverseExceptFirstTwo() {
        return " (" +
                "  (#5>) " +
                "  (# 2! ! \"( )\" 2-$) " +
                "  " + while_lb_l1__() +
                ")@";
    }

    /**
     * Register A
     */
    public static String welcome() {
        return "(Welcome to the Calculator!\n)\" " +   // print hello
                "(' " +                                // start of loop
                "   ? (@) ()" + if_b_l1_l2__() +         // print the number or execute the list
                "   " + clearInReverseExceptFirstTwo() + // print the stack, except the last two elements
                "   (\n)\" " +                         // print newline
                ")" + loop_l__();
    }

    /**
     * Register B
     *
     * void primeFactor(int number) {
     *     while (number > 1) {
     *         int factor = findSmallestFactor(number);
     *         System.out.print(factor + " ");
     *         number = number / factor;
     *     }
     * }
     */
    public static String printPrimeFactors_n__() {
        return "" +
                "  2! Z" +     // setup
                "  (z 1 >)" +  // while condition
                "  (z " + getSmallestFactor_n__n() + " 2!\" ( )\" z " + swap_x1_x2__x2_x1() + " / Z)" + // while body
                "  " + while_lb_l1__() +
                "  1$";        // cleanup
    }

}
