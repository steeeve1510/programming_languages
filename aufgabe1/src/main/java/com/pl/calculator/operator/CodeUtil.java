package com.pl.calculator.operator;

public class CodeUtil {

    /**
     * Register A
     */
    public static String welcome() {
        return "(Welcome to the Calculator!\n)\" " +   // print hello
                "(' " +                                // start of loop
                "   ? (@) () c@" +                     // print the number or execute the list
                "   " + clearInReverseExceptFirstTwo() + // print the stack, except the last two elements
                "   (\n)\" " +                         // print newline
                ") d@";                                // endless loop
    }
    private static String clearInReverseExceptFirstTwo() {
        return " (" +
                "  (#5>) " +                // while condition
                "  (# 2! ! \"( )\" 2-$) " + // while body
                "  e@" +                    // while call
                ")@";
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
     *
     * z: the number
     */
    public static String printPrimeFactors_n__() {
        return "" +
                "  2! Z" +      // setup
                "  (z 1 >)" +   // while condition
                "  (" +
                "    z " + getSmallestFactor_n__n() + // calculate next fraction
                "    2!\" ( )\" " + // print fraction
                "    z f@ / " +     // divide number through fraction
                "    Z" +           // store the new number
                "  )" + // while body
                "  e@ " +       // while call
                "  1$";         // cleanup
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
     *
     * x: the rest of each modulo calculation
     * y: the factor candidate
     */
    private static String getSmallestFactor_n__n() {
        return " (" +
                "  1Y 1X " +            // initialize
                "  (x 0 >)" +           // while condition
                "  (5! y1+ 2! Y % X)" + // while body
                "  e@" +                // while call
                "  1$ y" +              // cleanup
                ")@";
    }

    /**
     * Register C
     *
     * b:  true (1) or false 0
     * l1: code to be executed if false
     * l2: code to be executed if true
     */
    public static String if_b_l1_l2__() {
        return "4!4$1+$@";
    }

    /**
     * Register D
     *
     * l: this list will be looped forever
     */
    public static String loop_l__() {
        return "" +
                " (3!@2!@) " +
                "  3!@2!@  ";
    }

    /**
     * Register E
     *
     * lb: a list that evaluates to a boolean
     * l1: this list will be looped
     */
    public static String while_lb_l1__() {
        return "" +
                " (4!@ (1$1$1$) (3!@2!@) c@ )" + // register c is if
                "  4!@ (1$1$1$) (3!@2!@) c@";
    }

    /**
     * Register F
     */
    public static String swap_x1_x2__x2_x1() {
        return "3!3$";
    }
}
