package ua.edu.hneu.oop.java.lr2p2.task3;

import ua.edu.hneu.oop.java.util.ConsoleUtil;

import java.io.IOException;

public class MainClass {
    private static SyntaxAnalyser syntaxAnalyser;
    private static ConsoleUtil consoleUtil;


    public static void main(String[] args) {
        try {
            init();
            consoleUtil.printf("%s - %b", "x", syntaxAnalyser.isFormula("x"));
            consoleUtil.printf("%s - %b", "x+x", syntaxAnalyser.isFormula("x+x"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void init() throws IOException {
        syntaxAnalyser = new SyntaxAnalyser();
        syntaxAnalyser.init();
        consoleUtil = ConsoleUtil.getInstance();
    }
}
