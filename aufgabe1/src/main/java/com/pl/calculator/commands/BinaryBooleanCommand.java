package com.pl.calculator.commands;

import com.pl.calculator.components.DataStack;
import com.pl.calculator.exceptions.DivisionByZeroException;
import com.pl.calculator.exceptions.NoBooleanException;
import com.pl.calculator.model.Number;

public class BinaryBooleanCommand implements Command {
    private static final String VALID_COMMANDS = "&|";

    private DataStack dataStack;

    public BinaryBooleanCommand(DataStack dataStack) {
        this.dataStack = dataStack;
    }

    @Override
    public boolean isApplicable(char command) {
        return VALID_COMMANDS.indexOf(command) >= 0;
    }

    @Override
    public void execute(char command) {
        var top = dataStack.pop();
        var bottom = dataStack.pop();

        if (!(top instanceof Number)) {
            throw new NoBooleanException("Top element is no boolean: " + top.toOutputString());
        }
        if (!(bottom instanceof Number)) {
            throw new NoBooleanException("Bottom element is no boolean: " + bottom.toOutputString());
        }

        var topNumber = ((Number) top).get();
        var bottomNumber = ((Number) bottom).get();

        if (topNumber != 0 && topNumber != 1) {
            throw new NoBooleanException("Top number is no boolean: " + top.toOutputString());
        }
        if (bottomNumber != 0 && bottomNumber != 1) {
            throw new NoBooleanException("Bottom number is no boolean: " + bottom.toOutputString());
        }

        int newNumber = 0;
        switch (command) {
            case '&':
                if (topNumber == 1 && bottomNumber == 1) {
                    newNumber = 1;
                }
                break;
            case '|':
                if (topNumber == 1 || bottomNumber == 1) {
                    newNumber = 1;
                }
                break;
            default:
                throw new IllegalArgumentException();
        }

        dataStack.push(new Number(newNumber));
    }
}
