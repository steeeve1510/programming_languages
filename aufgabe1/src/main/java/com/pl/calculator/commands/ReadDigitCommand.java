package com.pl.calculator.commands;

import com.pl.calculator.components.DataStack;
import com.pl.calculator.components.OperationMode;
import com.pl.calculator.model.Number;

public class ReadDigitCommand implements Command {

    private DataStack dataStack;
    private OperationMode operationMode;

    public ReadDigitCommand(DataStack dataStack, OperationMode operationMode) {
        this.dataStack = dataStack;
        this.operationMode = operationMode;
    }

    @Override
    public boolean isApplicable(char command) {
        return Character.isDigit(command);
    }

    @Override
    public void execute(char command) {
        var number = Integer.valueOf(String.valueOf(command));
        dataStack.push(new Number(number));
        operationMode.setToIntegerConstructionMode();
    }
}
