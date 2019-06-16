package com.pl.calculator.commands;

import com.pl.calculator.components.DataStack;
import com.pl.calculator.exceptions.NoNumberException;
import com.pl.calculator.model.Number;

public class BinaryComparisionCommand implements Command {

    private static final String VALID_COMMANDS = "<>=";

    private DataStack dataStack;

    public BinaryComparisionCommand(DataStack dataStack) {
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
            throw new NoNumberException("Top element is no number: " + top.toOutputString());
        }
        if (!(bottom instanceof Number)) {
            throw new NoNumberException("Bottom element is no number: " + bottom.toOutputString());
        }

        var topNumber = ((Number) top).get();
        var bottomNumber = ((Number) bottom).get();

        int newNumber = 0;
        switch (command) {
            case '<':
                if (bottomNumber < topNumber) {
                    newNumber = 1;
                }
                break;
            case '>':
                if (bottomNumber > topNumber) {
                    newNumber = 1;
                }
                break;
            case '=':
                if (bottomNumber == topNumber) {
                    newNumber = 1;
                }
                break;
            default:
                throw new IllegalArgumentException();
        }

        dataStack.push(new Number(newNumber));
    }
}
