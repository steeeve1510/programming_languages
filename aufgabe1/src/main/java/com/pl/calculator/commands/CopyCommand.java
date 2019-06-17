package com.pl.calculator.commands;

import com.pl.calculator.components.DataStack;
import com.pl.calculator.exceptions.IllegalIndexException;
import com.pl.calculator.exceptions.NoNumberException;
import com.pl.calculator.model.Element;
import com.pl.calculator.model.Number;

import java.util.ArrayDeque;

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

        if (!(element instanceof Number)) {
            throw new NoNumberException();
        }
        var number = ((Number) element).get();

        var newElement = getElement(number);

        dataStack.pop();
        dataStack.push(newElement);
    }

    private Element getElement(int number) {
        if (number < 0) {
            throw new IllegalIndexException();
        }

        var tmpStack = new ArrayDeque<Element>();

        for (int i = 1; i < number; i++) {
            var el = dataStack.pop();
            tmpStack.push(el);
        }

        var element = dataStack.peek();

        for (var el : tmpStack) {
            dataStack.push(el);
        }

        return element;
    }
}
