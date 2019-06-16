package com.pl.calculator.commands;

import com.pl.calculator.components.DataStack;
import com.pl.calculator.exceptions.NoNumberException;
import com.pl.calculator.model.Number;

public class StackSizeCommand implements Command {

    private DataStack dataStack;

    public StackSizeCommand(DataStack dataStack) {
        this.dataStack = dataStack;
    }

    @Override
    public boolean isApplicable(char command) {
        return command == '#';
    }

    @Override
    public void execute(char command) {
        var size = dataStack.size();

        dataStack.push(new Number(size));
    }
}
