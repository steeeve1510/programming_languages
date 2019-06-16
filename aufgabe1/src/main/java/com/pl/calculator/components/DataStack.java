package com.pl.calculator.components;

import com.pl.calculator.exceptions.DataStackEmptyException;
import com.pl.calculator.model.Element;

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

    @Override
    public String toString() {
        return "DataStack{" +
                "stack=" + stack +
                '}';
    }
}
