package com.pl.calculator.components;

public class CommandStream {

    private String stream = "";

    public void prepend(String s) {
        stream = s + stream;
    }

    public void append(String s) {
        stream += s;
    }

    public char retrieve() {
        if (stream.isEmpty()) {
            return '.';
        }

        char command = stream.charAt(0);
        stream = stream.substring(1);
        return command;
    }

    public boolean isEmpty() {
        return stream.isEmpty();
    }

    @Override
    public String toString() {
        return "CommandStream{" +
                "stream='" + stream + '\'' +
                '}';
    }
}
