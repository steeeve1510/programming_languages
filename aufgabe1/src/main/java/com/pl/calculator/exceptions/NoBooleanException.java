package com.pl.calculator.exceptions;

public class NoBooleanException extends RuntimeException {

    public NoBooleanException() {
        super("Element is no boolean");
    }

    public NoBooleanException(String message) {
        super(message);
    }
}
