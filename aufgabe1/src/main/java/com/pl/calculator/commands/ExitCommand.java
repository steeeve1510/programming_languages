package com.pl.calculator.commands;

import com.pl.calculator.components.DataStack;
import com.pl.calculator.components.OutputStream;

public class ExitCommand implements Command {

    @Override
    public boolean isApplicable(char command) {
        return command == '.';
    }

    @Override
    public void execute(char command) {
        System.exit(0);
    }
}
