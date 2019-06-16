package com.pl.calculator.exceptions;

public class NoBooleanException extends RuntimeException {

    public NoBooleanException() {
    }

    public NoBooleanException(String message) {
        super(message);
    }
}
