package com.pl.calculator.modes;

import com.pl.calculator.components.DataStack;
import com.pl.calculator.components.OperationMode;
import com.pl.calculator.model.Number;

public class NumberConstructionMode {

    private DataStack dataStack;
    private OperationMode operationMode;

    public NumberConstructionMode(DataStack dataStack, OperationMode operationMode) {
        this.dataStack = dataStack;
        this.operationMode = operationMode;
    }

    public boolean execute(char command) {
        if (!Character.isDigit(command)) {
            operationMode.setToExecutionMode();
            return false;
        }

        var number = Integer.valueOf(String.valueOf(command));

        var element = dataStack.pop();
        if (!(element instanceof Number)) {
            throw new IllegalStateException();
        }

        var stackNumber = ((Number) element).get();

        var newNumber = stackNumber * 10 + number;

        dataStack.push(new Number(newNumber));
        return true;
    }
}
