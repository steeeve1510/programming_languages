package com.pl.calculator.exceptions;

public class DataStackEmptyException extends RuntimeException {

    public DataStackEmptyException() {
        super("Data stack is empty");
    }

    public DataStackEmptyException(String message) {
        super(message);
    }
}
