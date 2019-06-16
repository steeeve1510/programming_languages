package com.pl.calculator.commands;

import com.pl.calculator.components.DataStack;

public class CopyCommand implements Command {

    private DataStack dataStack;

    public CopyCommand(DataStack dataStack) {
        this.dataStack = dataStack;
    }

    @Override
    public boolean isApplicable(char command) {
        return command == '!';
    }

    @Override
    public void execute(char command) {
        var element = dataStack.peek();
        dataStack.push(element);
    }
}
