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
        registers.set('b', new List(CodeUtil.testing()));
        registers.set('c', new List(CodeUtil.if_b_l1_l2__()));
        registers.set('d', new List(CodeUtil.loop_l__()));
        registers.set('e', new List(CodeUtil.while_lb_l1__()));
        registers.set('f', new List(CodeUtil.swap_x1_x2__x2_x1()));
        registers.set('g', new List(CodeUtil.test_lb()));
        registers.set('h', new List(CodeUtil.printPrimeFactors_n__()));
        registers.set('i', new List(CodeUtil.getSmallestFactor_n__n()));
    }

    public void run() {
        initializeCommandStream();

        while (!commandStream.isEmpty()) {
            var command = commandStream.retrieve();

            if (operationMode.isInIntegerConstructionMode()) {
                numberConstructionMode.execute(command);
                if (operationMode.isInExecutionMode()) {
                    executionMode.execute(command);
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
