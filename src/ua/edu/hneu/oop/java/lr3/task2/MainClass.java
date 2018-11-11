package ua.edu.hneu.oop.java.lr3.task2;

import ua.edu.hneu.oop.java.util.ConsoleUtil;

import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainClass {
    private static final String REGEX = "(?muU)(((^|\\W)[eyuioa]\\w*)|(\\w*[eyuioa]($|\\W)))";
    private static final String TEXT = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";

    private static final String INVITATION = "Demonstration of work with RegexWorker class.%nWill be used pattern =\"%s\"%nAnd text =\"%s\"%n";
    private static final String DELIMITER_LINE = IntStream.range(0, 15).mapToObj(i -> "-").collect(Collectors.joining());
    private static final String PASSED_TEXT_CONTAINS_PATTERN_FORMAT = "Is passed text contains pattern matches - %b%n";
    private static final String ALL_PATTERN_MATCHES = "All pattern matches:";
    private static final String TEXT_AFTER_REMOVING_ALL_MATCHES_FORMAT = "Text after removing all matches - %s%n";

    public static void main(String[] args) {
        ConsoleUtil consoleUtil = ConsoleUtil.getInstance();

        RegexWorker regexWorker = new RegexWorker();
        regexWorker.setPattern(Pattern.compile(REGEX));
        regexWorker.setText(TEXT);

        consoleUtil.printf(INVITATION, regexWorker.getPattern().pattern(), regexWorker.getText());

        consoleUtil.println(DELIMITER_LINE);

        consoleUtil.printf(PASSED_TEXT_CONTAINS_PATTERN_FORMAT, regexWorker.contains());

        consoleUtil.println(DELIMITER_LINE);

        consoleUtil.println(ALL_PATTERN_MATCHES);
        regexWorker.printAllMatches();

        consoleUtil.println(DELIMITER_LINE);

        regexWorker.removeAllMatches();
        consoleUtil.printf(TEXT_AFTER_REMOVING_ALL_MATCHES_FORMAT, regexWorker.getText());
    }

}
