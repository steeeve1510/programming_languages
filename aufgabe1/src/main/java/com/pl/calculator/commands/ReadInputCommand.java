package com.pl.calculator.commands;

import com.pl.calculator.components.DataStack;
import com.pl.calculator.components.InputStream;
import com.pl.calculator.components.OperationMode;
import com.pl.calculator.model.Element;
import com.pl.calculator.model.List;
import com.pl.calculator.model.Number;
import com.pl.calculator.modes.ListConstructionMode;
import com.pl.calculator.modes.NumberConstructionMode;

public class ReadInputCommand implements Command {

    private DataStack dataStack;
    private InputStream inputStream;

    public ReadInputCommand(DataStack dataStack, InputStream inputStream) {
        this.dataStack = dataStack;
        this.inputStream = inputStream;
    }

    @Override
    public boolean isApplicable(char command) {
        return command == '\'';
    }

    @Override
    public void execute(char command) {
        var stream = inputStream.read();
        if (stream.isEmpty()) {
            dataStack.push(new List(""));
            return;
        }
        var firstChar = stream.charAt(0);
        var remainingStream = stream.substring(1);

        var tmpDataStack = new DataStack();
        var tmpOperationMode = new OperationMode();

        var readDigitCommand = new ReadDigitCommand(tmpDataStack, tmpOperationMode);
        var readListCommand = new ReadListCommand(tmpDataStack, tmpOperationMode);

        Element element = new List("");
        if (readDigitCommand.isApplicable(firstChar)) {
            element = readNumber(remainingStream, tmpDataStack, tmpOperationMode);
        }
        if (readListCommand.isApplicable(firstChar)) {
            element = readList(remainingStream, tmpDataStack, tmpOperationMode);
        }

        dataStack.push(element);
    }

    private Element readNumber(String remainingStream, DataStack dataStack, OperationMode operationMode) {
        dataStack.push(new Number(0));
        operationMode.setToIntegerConstructionMode();

        var numberConstructionMode = new NumberConstructionMode(dataStack, operationMode);

        for (char c : remainingStream.toCharArray()) {
            var executionDone = numberConstructionMode.execute(c);
            if (!executionDone) {
                return new List("");
            }
        }

        return dataStack.pop();
    }

    private Element readList(String remainingStream, DataStack dataStack, OperationMode operationMode) {
        dataStack.push(new List(""));
        operationMode.increaseListConstructionMode();

        var listConstructionMode = new ListConstructionMode(dataStack, operationMode);

        var chars = remainingStream.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            var command = chars[i];
            var endNotFound = listConstructionMode.execute(command);

            if (!endNotFound && (i + 1) != chars.length) {
                return new List("");
            }
        }

        return dataStack.pop();
    }
}
