package ua.edu.hneu.oop.java.lr2p2.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class SyntaxAnalyser {
    private static final String NAME_REGEX = "[xyz]";

    //Построить синтаксический анализатор для понятия формула.
    //формула ::= терм | терм + формула | терм – формула
    //терм ::= имя | (формула) | [формула] | {формула}
    //имя ::= x|y|z


    private List<String> errors;

    public void init() {
        errors = new ArrayList<>();
    }

    public List<String> getErrors() {
        return errors;
    }

    public boolean isName(String input) {
        if (Pattern.matches(NAME_REGEX, input)) {
            return true;
        }
        return false;
    }

    /**
     * терм ::= имя | (формула) | [формула] | {формула}
     */
    public boolean isTerm(String input) {
        return isName(input) ||                         // имя
                isBracketed(input, this::isFormula) ||  //(формула)
                isFormula(input) ||                     // [формула] - 1 time  or {формула}
                input.isEmpty();                        // [формула] - 0 time
    }

    public boolean isFormula(String input) {
        if (isTerm(input)) {
            return true;
        } else if (isCombination(input, "+", this::isTerm, this::isFormula)) {//split + here
            return true;
        } else if (isCombination(input, "-", this::isTerm, this::isFormula)) {//split + here
            return true;
        } else {
            return false;
        }
    }

    private boolean isCombination(String input, String connector, Predicate<String> firstPredicate, Predicate<String> secondPredicate) {
        return input.contains(connector) &&
                firstPredicate.test(input.substring(0, input.indexOf(connector))) &&
                secondPredicate.test((input.substring(input.indexOf(connector))));
    }

    private boolean isBracketed(String input, Predicate<String> bracedPredicate) {
        return input.startsWith("(") &&
                input.endsWith(")") &&
                bracedPredicate.test(input.substring(input.indexOf("("), input.lastIndexOf(")")));
    }

}
