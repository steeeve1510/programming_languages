package com.pl.calculator.operator;

public class CodeUtil {

    /**
     * b:  true (1) or false 0
     * l1: code to be executed if false
     * l2: code to be executed if true
     */
    public static String b_l1_l2_if() {
        return "(4!4$1+$@)@";
    }

    /**
     * l: this list will be looped forever
     */
    public static String l_loop() {
        return " (" +
                " (3!@2!@) " +
                "  3!@2!@  " +
                ")@";
    }

    /**
     * l: an action which takes an element from the stack
     * <p>
     * this will clear the stack, except the last two elements
     */
    public static String l_foreach_keep_2() {
        return " (" +
                " (#4= (4!4!@3$2!@) (1$1$) " + b_l1_l2_if() + " )" +
                "  #4= (4!4!@3$2!@) (1$1$) " + b_l1_l2_if() + "  " +
                ")@";
    }

    public static String welcome() {
        return "(Welcome to the Calculator!\n)\" " +   // print hello
                "(' " +                                // start of loop
                "   ? (@) ()" + b_l1_l2_if() +         // print the number or execute the list
                "   (\"( )\") " + l_foreach_keep_2() + // print the stack, except the last two elements
                "   (\n)\" " +                         // print newline
                ")" + l_loop();
    }
}
