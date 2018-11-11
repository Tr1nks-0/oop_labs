package ua.edu.hneu.oop.java.lr2.task1;


import ua.edu.hneu.oop.java.util.ConsoleUtil;

import java.io.IOException;

public class MainClass {
    private static final String EXIT_COMMAND = "\\exit";
    private static final String INVITATION = "Enter phrase for de-verbization, or type \"\\exit\" for exit.";
    private static final String IO_EXCEPTION_FORMAT = "Something went wrong with obtaining value. Original error is:\"%s\".%n%n";

    private static ConsoleUtil consoleUtil;
    private static VerbCutter verbCutter;

    public static void main(String[] args) {
        boolean run = true;
        try {
            init();
        } catch (IOException e) {
            consoleUtil.printf(IO_EXCEPTION_FORMAT, e.getMessage());
            run = false;
        }
        do {
            try {
                consoleUtil.println(INVITATION);
                String input = consoleUtil.readInput();
                if (checkIfExit(input)) {
                    break;
                }
                consoleUtil.println(verbCutter.cutVerbs(input));
            } catch (IOException e) {
                consoleUtil.printf(IO_EXCEPTION_FORMAT, e.getMessage());
                run = false;
            }

        } while (run);

    }

    private static boolean checkIfExit(String input) {
        return input.toLowerCase().contains(EXIT_COMMAND);
    }

    private static void init() throws IOException {
        consoleUtil = ConsoleUtil.getInstance();
        verbCutter = new VerbCutter();
    }
}

