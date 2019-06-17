package com.pl.calculator.operator;

import com.pl.calculator.components.*;
import com.pl.calculator.model.List;
import com.pl.calculator.modes.ExecutionMode;
import com.pl.calculator.modes.ListConstructionMode;
import com.pl.calculator.modes.NumberConstructionMode;

public class Operator {

    private CommandStream commandStream = new CommandStream();
    private OperationMode operationMode = new OperationMode();
    private DataStack dataStack = new DataStack();
    private Registers registers = new Registers();
    private InputStream inputStream = new InputStream();
    private OutputStream outputStream = new OutputStream();

    private ExecutionMode executionMode;
    private ListConstructionMode listConstructionMode;
    private NumberConstructionMode numberConstructionMode;

    public Operator() {
        executionMode = new ExecutionMode(commandStream, dataStack, operationMode, registers, inputStream, outputStream);
        listConstructionMode = new ListConstructionMode(dataStack, operationMode);
        numberConstructionMode = new NumberConstructionMode(dataStack, operationMode);

        registers.set('a', new List(CodeUtil.welcome()));
    }

    public void run() {
        initializeCommandStream();

        while (!commandStream.isEmpty()) {
            var command = commandStream.retrieve();

            if (operationMode.isInIntegerConstructionMode()) {
                var executed = numberConstructionMode.execute(command);
                if (!executed) {
                    commandStream.addInFront(String.valueOf(command));
                }
            } else if (operationMode.isInListConstructionMode()) {
                listConstructionMode.execute(command);
            } else if (operationMode.isInExecutionMode()) {
                executionMode.execute(command);
            } else {
                throw new IllegalStateException();
            }
        }
    }

    private void initializeCommandStream() {
        commandStream.addInFront("a@");
    }
}
