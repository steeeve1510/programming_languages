package com.pl.calculator.commands;

import com.pl.calculator.components.DataStack;
import com.pl.calculator.exceptions.IllegalIndexException;
import com.pl.calculator.exceptions.NoNumberException;
import com.pl.calculator.model.Element;
import com.pl.calculator.model.Number;

import java.util.ArrayDeque;

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
        var element = dataStack.pop();

        if (!(element instanceof Number)) {
            throw new NoNumberException();
        }
        var number = ((Number) element).get();

        removeElement(number);
    }

    private void removeElement(int number) {
        if (number <= 0) {
            throw new IllegalIndexException();
        }

        var tmpStack = new ArrayDeque<Element>();

        for (int i = 1; i < number; i++) {
            var el = dataStack.pop();
            tmpStack.push(el);
        }

        dataStack.pop();

        for (var el : tmpStack) {
            dataStack.push(el);
        }
    }
}
