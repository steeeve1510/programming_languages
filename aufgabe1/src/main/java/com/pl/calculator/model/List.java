package com.pl.calculator.model;

public class List implements Element {

    private String stream;

    public List(String stream) {
        this.stream = stream;
    }

    public String get() {
        return stream;
    }

    @Override
    public String toOutputString() {
        return stream;
    }


    @Override
    public String toString() {
        return "List{" +
                "stream='" + stream + '\'' +
                '}';
    }
}
