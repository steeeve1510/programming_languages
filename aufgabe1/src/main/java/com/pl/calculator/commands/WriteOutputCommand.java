package com.pl.calculator.commands;

import com.pl.calculator.components.DataStack;
import com.pl.calculator.components.InputStream;
import com.pl.calculator.components.OutputStream;
import com.pl.calculator.model.List;

public class WriteOutputCommand implements Command {

    private DataStack dataStack;
    private OutputStream outputStream;

    public WriteOutputCommand(DataStack dataStack, OutputStream outputStream) {
        this.dataStack = dataStack;
        this.outputStream = outputStream;
    }

    @Override
    public boolean isApplicable(char command) {
        return command == '"';
    }

    @Override
    public void execute(char command) {
        var element = dataStack.pop();

        outputStream.write(element.toOutputString());
    }
}
