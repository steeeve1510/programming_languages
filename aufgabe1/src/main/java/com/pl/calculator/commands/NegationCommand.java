package com.pl.calculator.commands;

import com.pl.calculator.components.DataStack;
import com.pl.calculator.exceptions.NoNumberException;
import com.pl.calculator.model.Number;

public class NegationCommand implements Command {

    private DataStack dataStack;

    public NegationCommand(DataStack dataStack) {
        this.dataStack = dataStack;
    }

    @Override
    public boolean isApplicable(char command) {
        return command == '~';
    }

    @Override
    public void execute(char command) {
        var element = dataStack.pop();

        if (!(element instanceof Number)) {
            throw new NoNumberException("Element is no number: " + element.toOutputString());
        }

        var number = ((Number) element).get();

        dataStack.push(new Number(-number));
    }
}
