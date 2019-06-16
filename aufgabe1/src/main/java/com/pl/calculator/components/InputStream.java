package com.pl.calculator.components;

import java.util.Scanner;

public class InputStream {

    private Scanner scanner;

    public InputStream() {
        this.scanner = new Scanner(System.in);
    }

    public String read() {
        return scanner.nextLine();
    }
}
