package ua.edu.hneu.oop.java.lr1.task1;

import ua.edu.hneu.oop.java.util.ConsoleUtil;

import java.io.IOException;
import java.util.regex.Pattern;

public class MainClass {
    private static final String EXIT_COMMAND = "exit";
    private static final Pattern DOUBLE_PATTERN = Pattern.compile("^-?\\d+([.,]\\d)?$");
    private static final String INVITATION = "For calculation function value enter x value " +
            "- number in range [" + FunctionCalculator.MIN + " ; " + FunctionCalculator.MAX + "].\n" +
            "For exit enter \"exit\"";
    private static final String ANSWER_FORMAT = "For x =%1$.3f function value = %2$.3f%n";
    private static final String NOT_VALID_NUMBER_EXCEPTION = "You enter not valid number. Please correct Your input and try again.";
    private static final String IO_EXCEPTION_FORMAT = "Something went wrong with obtaining value. Original error is:\"%s\".%n%n";
    private static final String ILLEGAL_ARGUMENT_EXCEPTION_FORMAT = "%s Please correct Your input and try again.%n";
    private static final String EMPTY_PARAMETERS_MESSAGE = "Expected one parameter - double number. Obtained 0.";
    private static final String WRONG_FIRST_PARAMETER_FORMAT = "Wrong first parameter. Expected double, got:%s%n";
    private static final String PARAMETER_OUT_OF_RANGE_MESSAGE = "parameter value is out of range";

    private static ConsoleUtil consoleUtil;
    private static FunctionCalculator functionCalculator;

    public static void main(String[] args) {
        init();
        boolean run = true;
        do {
            consoleUtil.println(INVITATION);
            try {
                String input = consoleUtil.readInput();
                if (checkIfExit(input)) {
                    break;
                }
                double x = extractX(input);
                double y = functionCalculator.calculate(x);
                consoleUtil.printf(ANSWER_FORMAT, x, y);
            } catch (NumberFormatException e) {
                consoleUtil.print(NOT_VALID_NUMBER_EXCEPTION);
            } catch (IllegalArgumentException e) {
                consoleUtil.printf(ILLEGAL_ARGUMENT_EXCEPTION_FORMAT, e.getMessage());
            } catch (IOException e) {
                consoleUtil.printf(IO_EXCEPTION_FORMAT, e.getMessage());
                run = false;
            }
        } while (run);

    }

    private static double extractX(String input) throws IllegalArgumentException {
        String[] splitInput = consoleUtil.splitInput(input);
        if (splitInput.length < 1) {
            throw new IllegalArgumentException(EMPTY_PARAMETERS_MESSAGE);
        }
        String xString = splitInput[0];
        if (!consoleUtil.validateInput(DOUBLE_PATTERN, xString)) {
            throw new IllegalArgumentException(String.format(WRONG_FIRST_PARAMETER_FORMAT, xString));
        }
        double x = Double.parseDouble(xString);
        if (!functionCalculator.isInRange(x)) {
            throw new IllegalArgumentException(PARAMETER_OUT_OF_RANGE_MESSAGE);
        }
        return x;
    }

    private static boolean checkIfExit(String input) {
        return input.toLowerCase().contains(EXIT_COMMAND);
    }

    private static void init() {
        consoleUtil = ConsoleUtil.getInstance();
        functionCalculator = new FunctionCalculator();
    }
}
