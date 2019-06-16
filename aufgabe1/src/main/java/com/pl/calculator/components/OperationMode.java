package com.pl.calculator.components;

public class OperationMode {

    private int mode = 0;

    public int getMode() {
        return mode;
    }

    public boolean isInExecutionMode() {
        return mode == 0;
    }

    public boolean isInIntegerConstructionMode() {
        return mode < 0;
    }

    public boolean isInListConstructionMode() {
        return mode > 0;
    }

    public void setToExecutionMode() {
        mode = 0;
    }

    public void setToIntegerConstructionMode() {
        mode = -1;
    }

    public void increaseListConstructionMode() {
        if (mode < 0) {
            mode = 0;
        }
        mode++;
    }

    public void decreaseListConstructionMode() {
        mode--;
    }
}
