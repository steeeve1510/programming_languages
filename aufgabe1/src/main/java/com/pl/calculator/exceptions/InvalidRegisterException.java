package com.pl.calculator.exceptions;

public class InvalidRegisterException extends RuntimeException {

    public InvalidRegisterException() {
    }

    public InvalidRegisterException(String message) {
        super(message);
    }
}
