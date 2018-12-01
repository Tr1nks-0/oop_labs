package ua.edu.hneu.oop.java.lr5.task2;

import ua.edu.hneu.oop.java.util.ConsoleUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

public class StudentFactory {
    private static final String SURNAME_REGEX = "(?u)\\p{L}+ *";
    private static final String INITIALS_REGEX = "(\\p{L}+\\.)( ?\\p{L}+\\.)? *";
    private static final String ARRAY_DELIMITERS = "[;,:]";
    private static final String INT_ARRAY_REGEX = "\\d *(" + ARRAY_DELIMITERS + " *\\d)* *";

    private static final String SURNAME_INVITATION = "Enter student's surname:";
    private static final String INITIALS_INVITATION = "Enter student's initials:";
    private static final String GROUP_INVITATION = "Enter student's group number:";
    private static final String MARKS_INVITATION = "Enter student's marks in format \"4,5,3,2,4\":";
    private static final String WRONG_INPUT = "Wrong input. Please try again." + System.lineSeparator();

    private ConsoleUtil consoleUtill;

    public StudentFactory(ConsoleUtil consoleUtil) {
        this.consoleUtill = consoleUtil;
    }

    public Student createStudent() throws IOException {
        consoleUtill.println(SURNAME_INVITATION);
        String surname = obtainString(this::validateSurname);
        consoleUtill.println(INITIALS_INVITATION);
        String initials = obtainString(this::validateInitials);
        consoleUtill.println(GROUP_INVITATION);
        int group = obtainInt(this::validateGroup);
        consoleUtill.println(MARKS_INVITATION);
        int[] marks = obtainIntArr();
        return new Student(surname, initials, group, marks);
    }

    private String obtainString(Predicate<String> validator) throws IOException {
        do {
            String input = consoleUtill.readInput();
            if (validator.test(input)) {
                return input.trim();
            }
            consoleUtill.print(WRONG_INPUT);
        } while (true);
    }

    private int obtainInt(Predicate<Integer> validator) throws IOException {
        do {
            int input = Integer.parseInt(consoleUtill.readInput());
            if (validator.test(input)) {
                return input;
            }
            consoleUtill.print(WRONG_INPUT);
        } while (true);
    }

    private int[] obtainIntArr() throws IOException {
        do {
            String input = consoleUtill.readInput();
            if (validateMarksArrayString(input)) {
                int[] arr = Arrays.stream(input.split(ARRAY_DELIMITERS)).map(String::trim).mapToInt(Integer::parseInt).toArray();
                if (arr.length == Student.MARKS_SIZE) {
                    return arr;
                }
            }
            consoleUtill.print(WRONG_INPUT);
        } while (true);
    }

    private boolean validateSurname(String surname) {
        return Objects.nonNull(surname) &&
                surname.matches(SURNAME_REGEX);
    }

    private boolean validateInitials(String initials) {
        return Objects.nonNull(initials) &&
                initials.matches(INITIALS_REGEX);
    }

    private boolean validateGroup(int group) {
        return group > 0;
    }

    private boolean validateMarksArrayString(String input) {
        return Objects.nonNull(input) &&
                input.matches(INT_ARRAY_REGEX);
    }
}
