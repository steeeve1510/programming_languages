package com.pl.calculator.commands;

import com.pl.calculator.components.DataStack;

public class PrintDataStackCommand implements Command {

    private DataStack dataStack;

    public PrintDataStackCommand(DataStack dataStack) {
        this.dataStack = dataStack;
    }

    @Override
    public boolean isApplicable(char command) {
        return command == 'µ';
    }

    @Override
    public void execute(char command) {
        System.out.println("DataStack:");
        System.out.println(dataStack.toPrettyString());
    }
}
