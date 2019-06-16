package com.pl.calculator.commands;

public interface Command {

    boolean isApplicable(char command);

    void execute(char command);
}
