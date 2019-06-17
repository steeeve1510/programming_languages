package com.pl.calculator.commands;

import com.pl.calculator.components.DataStack;
import com.pl.calculator.model.Number;

public class CheckIfNumberCommand implements Command {

    private DataStack dataStack;

    public CheckIfNumberCommand(DataStack dataStack) {
        this.dataStack = dataStack;
    }

    @Override
    public boolean isApplicable(char command) {
        return command == '?';
    }

    @Override
    public void execute(char command) {
        var element = dataStack.peek();

        var isNumber = 0;
        if (element instanceof Number) {
            isNumber = 1;
        }

        dataStack.push(new Number(isNumber));
    }
}
