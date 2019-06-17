package com.pl.calculator.commands;

import com.pl.calculator.components.CommandStream;

public class ExitCommand implements Command {

    private CommandStream commandStream;

    public ExitCommand(CommandStream commandStream) {
        this.commandStream = commandStream;
    }

    @Override
    public boolean isApplicable(char command) {
        return command == '.';
    }

    @Override
    public void execute(char command) {
        while (!commandStream.isEmpty()) {
            commandStream.retrieve();
        }
    }
}
