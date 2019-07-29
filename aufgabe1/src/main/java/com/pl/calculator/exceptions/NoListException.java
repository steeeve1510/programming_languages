package com.pl.calculator.exceptions;

public class NoListException extends RuntimeException{

    public NoListException() {
        super("Element is no list");
    }

    public NoListException(String message) {
        super(message);
    }
}
