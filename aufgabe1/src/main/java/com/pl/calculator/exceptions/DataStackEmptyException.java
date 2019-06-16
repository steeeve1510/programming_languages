package com.pl.calculator.exceptions;

public class DataStackEmptyException extends RuntimeException {

    public DataStackEmptyException() {
    }

    public DataStackEmptyException(String message) {
        super(message);
    }
}
