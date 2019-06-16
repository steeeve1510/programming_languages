package com.pl.calculator.commands;

import com.pl.calculator.components.DataStack;
import com.pl.calculator.components.Registers;
import com.pl.calculator.model.Element;

public class PopToRegisterCommand implements Command {

    private DataStack dataStack;
    private Registers registers;

    public PopToRegisterCommand(DataStack dataStack, Registers registers) {
        this.registers = registers;
        this.dataStack = dataStack;
    }

    @Override
    public boolean isApplicable(char command) {
        String upperCaseRegisterNames = Registers.NAMES.toUpperCase();
        return upperCaseRegisterNames.indexOf(command) >= 0;
    }

    @Override
    public void execute(char command) {
        Element value = dataStack.pop();
        registers.set(command, value);
    }
}
