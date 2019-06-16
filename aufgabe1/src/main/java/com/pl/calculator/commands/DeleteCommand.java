package com.pl.calculator.commands;

import com.pl.calculator.components.DataStack;

public class DeleteCommand implements Command {

    private DataStack dataStack;

    public DeleteCommand(DataStack dataStack) {
        this.dataStack = dataStack;
    }

    @Override
    public boolean isApplicable(char command) {
        return command == '$';
    }

    @Override
    public void execute(char command) {
        dataStack.pop();
    }
}
