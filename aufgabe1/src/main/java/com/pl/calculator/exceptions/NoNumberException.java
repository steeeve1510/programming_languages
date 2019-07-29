package com.pl.calculator.exceptions;

public class NoNumberException extends RuntimeException{

    public NoNumberException() {
        super("Element is no number");
    }

    public NoNumberException(String message) {
        super(message);
    }
}
