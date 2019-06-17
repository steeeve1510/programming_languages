package com.pl.calculator.components;

import com.pl.calculator.exceptions.DataStackEmptyException;
import com.pl.calculator.model.Element;
import com.pl.calculator.model.List;

import java.util.ArrayDeque;
import java.util.Deque;

public class DataStack {

    private Deque<Element> stack = new ArrayDeque<>();

    public void push(Element element) {
        stack.push(element);
    }

    public Element pop() {
        if (stack.isEmpty()) {
            throw new DataStackEmptyException();
        }

        return stack.pop();
    }

    public Element peek() {
        if (stack.isEmpty()) {
            throw new DataStackEmptyException();
        }

        return stack.peek();
    }

    public int size() {
        return stack.size();
    }

    public String toPrettyString() {
        String ret = "";
        for (var e : stack) {
            if (e instanceof List) {
                ret = "(" + e.toOutputString() + ") " + ret;
            } else {
                ret = e.toOutputString() + " " + ret;
            }
        }
        return ret;
    }

    @Override
    public String toString() {
        return "DataStack{" +
                "stack=" + stack +
                '}';
    }
}
