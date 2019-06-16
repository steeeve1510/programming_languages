package com.pl.calculator.exceptions;

public class NoNumberException extends RuntimeException{

    public NoNumberException() {
    }

    public NoNumberException(String message) {
        super(message);
    }
}
