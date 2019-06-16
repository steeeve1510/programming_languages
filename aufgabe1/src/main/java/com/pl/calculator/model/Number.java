package com.pl.calculator.model;

public class Number implements Element {

    private int number;

    public Number(int number) {
        this.number = number;
    }

    public int get() {
        return number;
    }

    @Override
    public String toOutputString() {
        return String.valueOf(number);
    }

    @Override
    public String toString() {
        return "Number{" +
                "number=" + number +
                '}';
    }
}
