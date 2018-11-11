package ua.edu.hneu.oop.java.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class ConsoleUtil {
    private static final String PARAMETER_DECIMETER = " ";
    private static ConsoleUtil instance;
    private BufferedReader reader;


    private ConsoleUtil() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static ConsoleUtil getInstance() {
        if (instance == null) {
            instance = new ConsoleUtil();
        }
        return instance;
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
        return input.trim().split(PARAMETER_DECIMETER);
    }

    public void printf(String format, Object... values) {
        System.out.printf(format, values);
    }
}
