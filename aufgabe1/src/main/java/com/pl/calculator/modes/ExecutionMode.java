package com.pl.calculator.modes;

import com.pl.calculator.commands.*;
import com.pl.calculator.components.*;

import java.util.ArrayList;
import java.util.List;

public class ExecutionMode {

    private CommandStream commandStream;
    private DataStack dataStack;
    private OperationMode operationMode;
    private Registers registers;
    private InputStream inputStream;
    private OutputStream outputStream;

    private List<Command> commands = new ArrayList<>();

    public ExecutionMode(CommandStream commandStream, DataStack dataStack, OperationMode operationMode, Registers registers, InputStream inputStream, OutputStream outputStream) {
        this.commandStream = commandStream;
        this.dataStack = dataStack;
        this.operationMode = operationMode;
        this.registers = registers;
        this.inputStream = inputStream;
        this.outputStream = outputStream;

        initialize();
    }

    private void initialize() {
        commands.add(new ApplyLaterCommand(dataStack, commandStream));
        commands.add(new ApplyNowCommand(dataStack, commandStream));
        commands.add(new BinaryBooleanCommand(dataStack));
        commands.add(new BinaryComparisionCommand(dataStack));
        commands.add(new BinaryNumberCommand(dataStack));
        commands.add(new CheckIfNumberCommand(dataStack));
        commands.add(new CopyCommand(dataStack));
        commands.add(new DeleteCommand(dataStack));
        commands.add(new ExitCommand(commandStream));
        commands.add(new NegationCommand(dataStack));
        commands.add(new PopToRegisterCommand(dataStack, registers));
        commands.add(new PushRegisterCommand(dataStack, registers));
        commands.add(new ReadDigitCommand(dataStack, operationMode));
        commands.add(new ReadInputCommand(dataStack, inputStream));
        commands.add(new ReadListCommand(dataStack, operationMode));
        commands.add(new StackSizeCommand(dataStack));
        commands.add(new WriteOutputCommand(dataStack, outputStream));
    }

    public boolean execute(char streamCommand) {
        var command = commands.stream()
                .filter(c -> c.isApplicable(streamCommand))
                .findFirst()
                .orElse(null);

        if (command == null) {
            return false;
        }

        command.execute(streamCommand);
        return true;
    }
}
