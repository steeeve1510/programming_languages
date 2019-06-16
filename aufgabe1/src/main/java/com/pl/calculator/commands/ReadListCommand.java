package com.pl.calculator.commands;

import com.pl.calculator.components.DataStack;
import com.pl.calculator.components.OperationMode;
import com.pl.calculator.model.List;

public class ReadListCommand implements Command {

    private DataStack dataStack;
    private OperationMode operationMode;

    public ReadListCommand(DataStack dataStack, OperationMode operationMode) {
        this.dataStack = dataStack;
        this.operationMode = operationMode;
    }

    @Override
    public boolean isApplicable(char command) {
        return command == '(';
    }

    @Override
    public void execute(char command) {
        dataStack.push(new List(""));
        operationMode.increaseListConstructionMode();
    }
}
