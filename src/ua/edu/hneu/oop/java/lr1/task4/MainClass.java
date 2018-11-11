package ua.edu.hneu.oop.java.lr1.task4;

import ua.edu.hneu.oop.java.util.ConsoleUtil;

import java.io.IOException;
import java.util.regex.Pattern;

public class MainClass {
    private static final Pattern INT_PATTERN = Pattern.compile("^\\d+$");
    private static final String EXIT_COMMAND = "exit";
    private static final String INVITATION = "Enter matrix sizes or for exit type \"exit\"";
    private static final String ANSWER_FORMAT = "Original matrix:%n%s%nCompacted matrix:%n%s%n";
    private static final String NOT_VALID_NUMBER_EXCEPTION = "You enter not valid number. Please correct Your input and try again.";
    private static final String IO_EXCEPTION_FORMAT = "Something went wrong with obtaining value. Original error is:\"%s\".%n%n";
    private static final String ILLEGAL_ARGUMENT_EXCEPTION_FORMAT = "%s Please correct Your input and try again.%n";
    private static final String EMPTY_PARAMETERS_MESSAGE = "Expected two parameters - int number. Obtained 0.";
    private static final String WRONG_PARAMETER_FORMAT = "Wrong parameter. Expected int, got:%s%n";
    private static final String PARAMETER_OUT_OF_RANGE_MESSAGE = "parameter value is out of range. Should be more than 0";
    private static ConsoleUtil consoleUtil;
    private static MatrixProcessor matrixProcessor;

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
                int sizeX = extractFirstSize(input);
                int sizeY = extractSecondSize(input);
                int[][] matrix = matrixProcessor.obtainMatrix(sizeX, sizeY);
                int[][] result = matrixProcessor.compactMatrix(matrix, sizeX, sizeY);
                System.out.printf(ANSWER_FORMAT, matrixProcessor.matrixToString(matrix), matrixProcessor.matrixToString(result));
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

    private static int extractFirstSize(String input) throws IllegalArgumentException {
        return extractSize(input, 0);
    }

    private static int extractSecondSize(String input) throws IllegalArgumentException {
        return extractSize(input, 1);
    }

    private static int extractSize(String input, int i) {
        String[] splitInput = consoleUtil.splitInput(input);
        if (splitInput.length < 2) {
            throw new IllegalArgumentException(EMPTY_PARAMETERS_MESSAGE);
        }
        return extractInt(splitInput[i]);
    }

    private static int extractInt(String str) throws IllegalArgumentException {

        if (!consoleUtil.validateInput(INT_PATTERN, str)) {
            throw new IllegalArgumentException(String.format(WRONG_PARAMETER_FORMAT, str));
        }
        int x = Integer.parseInt(str);
        if (x < 1) {
            throw new IllegalArgumentException(PARAMETER_OUT_OF_RANGE_MESSAGE);
        }
        return x;
    }

    private static boolean checkIfExit(String input) {
        return input.toLowerCase().contains(EXIT_COMMAND);
    }

    private static void init() {
        consoleUtil = ConsoleUtil.getInstance();
        matrixProcessor = new MatrixProcessor();
    }
}
