package com.pl.calculator.commands;

import com.pl.calculator.components.DataStack;
import com.pl.calculator.components.Registers;
import com.pl.calculator.model.Element;

public class PushRegisterCommand implements Command {

    private DataStack dataStack;
    private Registers registers;

    public PushRegisterCommand(DataStack dataStack, Registers registers) {
        this.dataStack = dataStack;
        this.registers = registers;
    }

    @Override
    public boolean isApplicable(char command) {
        String lowerCaseRegisterNames = Registers.NAMES.toLowerCase();
        return lowerCaseRegisterNames.indexOf(command) >= 0;
    }

    @Override
    public void execute(char command) {
        Element value = registers.get(command);
        dataStack.push(value);
    }
}
