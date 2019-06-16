package com.pl.calculator.commands;

import com.pl.calculator.components.CommandStream;
import com.pl.calculator.components.DataStack;
import com.pl.calculator.exceptions.NoListException;
import com.pl.calculator.model.List;

public class ApplyNowCommand implements Command {

    private DataStack dataStack;
    private CommandStream commandStream;

    public ApplyNowCommand(DataStack dataStack, CommandStream commandStream) {
        this.dataStack = dataStack;
        this.commandStream = commandStream;
    }

    @Override
    public boolean isApplicable(char command) {
        return command == '@';
    }

    @Override
    public void execute(char command) {
        var element = dataStack.pop();

        if (!(element instanceof List)) {
            throw new NoListException();
        }

        var stream = ((List) element).get();

        commandStream.addInFront(stream);
    }
}
