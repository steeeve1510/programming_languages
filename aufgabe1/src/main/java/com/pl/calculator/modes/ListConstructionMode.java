package com.pl.calculator.modes;

import com.pl.calculator.components.DataStack;
import com.pl.calculator.components.OperationMode;
import com.pl.calculator.model.List;

public class ListConstructionMode {

    private DataStack dataStack;
    private OperationMode operationMode;

    public ListConstructionMode(DataStack dataStack, OperationMode operationMode) {
        this.dataStack = dataStack;
        this.operationMode = operationMode;
    }

    public boolean execute(char command) {
        if (command == '(') {
            operationMode.increaseListConstructionMode();
        }
        if (command == ')') {
            operationMode.decreaseListConstructionMode();
        }

        if (operationMode.getMode() == 0) {
            return false;
        }

        var element = dataStack.pop();
        if (!(element instanceof List)) {
            throw new IllegalStateException();
        }

        var stream = ((List) element).get();

        stream += String.valueOf(command);

        dataStack.push(new List(stream));
        return true;
    }
}
