package com.pl.calculator.exceptions;

public class IllegalIndexException extends RuntimeException {

    public IllegalIndexException() {
        super("Illegal index");
    }

    public IllegalIndexException(String message) {
        super(message);
    }
}
