package com.pl.calculator.commands;

import com.pl.calculator.components.DataStack;
import com.pl.calculator.exceptions.DivisionByZeroException;
import com.pl.calculator.exceptions.NoNumberException;
import com.pl.calculator.model.Number;

public class BinaryNumberCommand implements Command {

    private static final String VALID_COMMANDS = "+-*/%";

    private DataStack dataStack;

    public BinaryNumberCommand(DataStack dataStack) {
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

        int newNumber;
        switch (command) {
            case '+':
                newNumber = bottomNumber + topNumber;
                break;
            case '-':
                newNumber = bottomNumber - topNumber;
                break;
            case '*':
                newNumber = bottomNumber * topNumber;
                break;
            case '/':
                if (topNumber == 0) {
                    throw new DivisionByZeroException();
                }
                newNumber = bottomNumber / topNumber;
                break;
            case '%':
                if (topNumber == 0) {
                    throw new DivisionByZeroException();
                }
                newNumber = bottomNumber % topNumber;
                break;
            default:
                throw new IllegalArgumentException();
        }

        dataStack.push(new Number(newNumber));
    }
}
