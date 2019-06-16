package com.pl.calculator.exceptions;

public class NoListException extends RuntimeException{

    public NoListException() {
    }

    public NoListException(String message) {
        super(message);
    }
}
