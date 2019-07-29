package com.pl.calculator.exceptions;

public class InvalidRegisterException extends RuntimeException {

    public InvalidRegisterException() {
        super("Register is invalid");
    }

    public InvalidRegisterException(String message) {
        super(message);
    }
}
