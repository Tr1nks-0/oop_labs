package ua.edu.hneu.oop.java.lr3.task3;

import ua.edu.hneu.oop.java.util.ConsoleUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainClass {
    //  1) Создать абстрактный класс Function с методом вычисления значения функции y=f(x) в заданной точке.
//  2) Создать производные классы: Line (y=ax+b), Kub (y=ax2+bx+c), Hyperbola ( y=
//  a
//  x
//  b )
//  со своими методами вычисления значения в заданной точке.
//  3) Создать массив n функций и вывести полную информацию о значении данных
//  функций в точке х.
    private static final Pattern DOUBLE_PATTERN = Pattern.compile("^-?\\d+([.,]\\d)?$");
    private static final String NOT_VALID_NUMBER_EXCEPTION = "You enter not valid number. Please correct Your input and try again.";
    private static final String IO_EXCEPTION_FORMAT = "Something went wrong with obtaining value. Original error is:\"%s\".%n%n";
    private static final String ILLEGAL_ARGUMENT_EXCEPTION_FORMAT = "%s Please correct Your input and try again.%n";
    private static final String EMPTY_PARAMETERS_MESSAGE = "Expected one parameter - double number. Obtained 0.";
    private static final String WRONG_FIRST_PARAMETER_FORMAT = "Wrong first parameter. Expected double, got:%s%n";
    private static final String INVITATION = "Enter x value";
    private static final String DELIMITER_LINE = IntStream.range(0, 15).mapToObj(i -> "-").collect(Collectors.joining());

    private static final double a = 12.2;
    private static final double b = 8.75;
    private static final double c = -1.44;
    public static final String PARAM_FORMAT = "%s=%.3f";
    public static final String HEADER_FORMAT = "For function %s=%s%nwith parameters:%n%s%nwhen x=%.3f%n";
    public static final String VALUE_FORMAT = "value = %.3f";
    public static final String VALUE_EXCEPTION_FORMAT = "value can not be calculated due %s";
    public static final String TAIL_FORMAT = "%n%s%n";

    private static ConsoleUtil consoleUtil;
    private static List<Function> functions;


    public static void main(String[] args) {
        boolean run = true;
        do {
            try {
                init();

                consoleUtil.println(INVITATION);
                String input = consoleUtil.readInput();
                double x = extractX(input);

                functions.forEach(function -> {
                    String name = function.getClass().getSimpleName();
                    String form = function.toString();
                    String params = parametersToString(function.getParameters());

                    consoleUtil.printf(HEADER_FORMAT, name, form, params, x);

                    try {
                        double value = function.calculate(x);
                        consoleUtil.printf(VALUE_FORMAT, value);
                    } catch (ArithmeticException e) {
                        consoleUtil.printf(VALUE_EXCEPTION_FORMAT, e.getMessage());
                    }

                    consoleUtil.printf(TAIL_FORMAT, DELIMITER_LINE);
                });

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

    private static String parametersToString(Map<String, Double> parameters) {
        return parameters.entrySet().stream()
                .map(e -> String.format(PARAM_FORMAT, e.getKey(), e.getValue()))
                .collect(Collectors.joining(System.lineSeparator()));
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
        return Double.parseDouble(xString);
    }

    private static void init() {
        consoleUtil = ConsoleUtil.getInstance();
        functions = new ArrayList<>();
        functions.add(new Line(a, b));
        functions.add(new Kub(a, b, c));
        functions.add(new Hyperbola(a, b));
    }
}
