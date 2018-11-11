package ua.edu.hneu.oop.java.lr2p2.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SyntaxAnalyser {
    private static final String SIGN_GROUP_NAME = "sign";
    private static final String CONTENT_GROUP_NAME = "content";
    private static final String NAME_REGEX = "[xyz]";
    private static final Pattern SUM_COMBINATION_PATTERN = Pattern.compile("((\\(.*\\))|(\\{.*})|(\\[.*])|[xyz]) *(?<" + SIGN_GROUP_NAME + ">[+-]) *((\\(.*\\))|(\\{.*})|(\\[.*])|[xyz])");
    private static final Pattern[] BRACKETED_REGEXES = {
            Pattern.compile("^(\\()(?<" + CONTENT_GROUP_NAME + ">.*)(\\))$"),
            Pattern.compile("^(\\[)(?<" + CONTENT_GROUP_NAME + ">.*)(])$"),
            Pattern.compile("^(\\{)(?<" + CONTENT_GROUP_NAME + ">.*)(})$")
    };
    private List<String> errors;

    public void init() {
        errors = new ArrayList<>();
    }

    public List<String> getErrors() {
        return errors;
    }

    /**
     * имя ::= x|y|z
     */
    public boolean isName(String input) {
        String trimmed = input.trim();
        if (!trimmed.isEmpty() && trimmed.trim().matches(NAME_REGEX)) {
            return true;
        }
        return false;
    }

    /**
     * терм ::= имя | (формула) | [формула] | {формула}
     */
    public boolean isTerm(String input) {
        return isName(input) ||
                isBracketed(input, this::isFormula);
    }

    /**
     * формула ::= терм | терм + формула | терм – формула
     */
    public boolean isFormula(String input) {
        return isSumCombination(input, this::isTerm, this::isFormula) ||
                isTerm(input);
    }

    private boolean isSumCombination(String input, Predicate<String> firstPredicate, Predicate<String> secondPredicate) {
        String trimmed = input.trim();
        Matcher matcher = SUM_COMBINATION_PATTERN.matcher(trimmed);
        if (matcher.matches()) {
            int index = trimmed.indexOf(matcher.group(SIGN_GROUP_NAME));
            return firstPredicate.test(trimmed.substring(0, index)) &&
                    secondPredicate.test(trimmed.substring(index + 1));
        }
        return false;
    }


    private boolean isBracketed(String input, Predicate<String> bracedPredicate) {
        String trimmed = input.trim();
        for (Pattern pattern : BRACKETED_REGEXES) {
            Matcher matcher = pattern.matcher(trimmed);
            if (matcher.matches()) {
                return bracedPredicate.test(matcher.group(CONTENT_GROUP_NAME));
            }
        }
        return false;
    }
}
