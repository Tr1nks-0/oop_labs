package ua.edu.hneu.oop.java.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class ConsoleUtil {
    private static final String PAREMETER_DELIMETER = " ";
    private BufferedReader reader;

    public ConsoleUtil() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void println(String text) {
        System.out.println(text);
    }

    public void print(String text) {
        System.out.print(text);
    }

    public String readInput() throws IOException {
        return reader.readLine();
    }

    public boolean validateInput(Pattern pattern, String input) {
        return pattern.matcher(input).matches();
    }

    public String[] splitInput(String input) {
        return input.trim().split(PAREMETER_DELIMETER);
    }

    public void printf(String format, Object... values) {
        System.out.printf(format, values);
    }
}
