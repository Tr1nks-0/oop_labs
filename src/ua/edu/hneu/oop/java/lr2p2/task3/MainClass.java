package ua.edu.hneu.oop.java.lr2p2.task3;

import ua.edu.hneu.oop.java.util.ConsoleUtil;

import java.io.IOException;

public class MainClass {
    private static final String EXIT_COMMAND = "exit";
    private static final String INVITATION = "Enter testing formula or type \"exit\" for exit";
    private static final String IO_EXCEPTION_FORMAT = "Something went wrong with obtaining value. Original error is:\"%s\".%n%n";
    private static final String ANSWER_FORMAT = "Entered formula is %s%n";

    private static SyntaxAnalyser syntaxAnalyser;
    private static ConsoleUtil consoleUtil;

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

                consoleUtil.printf(ANSWER_FORMAT, syntaxAnalyser.isFormula(input) ? "VALID" : "INVALID");

            } catch (IOException e) {
                consoleUtil.printf(IO_EXCEPTION_FORMAT, e.getMessage());
                run = false;
            }
        } while (run);
    }

    private static boolean checkIfExit(String input) {
        return input.toLowerCase().contains(EXIT_COMMAND);
    }

    private static void init() {
        syntaxAnalyser = new SyntaxAnalyser();
        syntaxAnalyser.init();
        consoleUtil = ConsoleUtil.getInstance();
    }
}
