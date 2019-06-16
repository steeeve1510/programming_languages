package com.pl.calculator.components;

import com.pl.calculator.exceptions.InvalidRegisterException;
import com.pl.calculator.model.Element;
import com.pl.calculator.model.Number;

import java.util.HashMap;
import java.util.Map;

public class Registers {

    public static final String NAMES = "abcdefghijklmnopqrstuvwxyz";

    private Map<Character, Element> registers = new HashMap<>();

    public Registers() {
        for (char c : NAMES.toCharArray()) {
            registers.put(c, new Number(0));
        }
    }

    public Element get(char register) {
        char reg = Character.toLowerCase(register);
        if (NAMES.indexOf(reg) < 0) {
            throw new InvalidRegisterException("Register '" + register + "' does not exist");
        }

        return registers.get(reg);
    }

    public void set(char register, Element element) {
        char reg = Character.toLowerCase(register);
        if (NAMES.indexOf(reg) < 0) {
            throw new InvalidRegisterException("Register '" + register + "' does not exist");
        }
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }

        registers.put(reg, element);
    }
}
