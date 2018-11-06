package ua.edu.hneu.oop.java.lr1.task2;

import ua.edu.hneu.oop.java.util.ConsoleUtil;

public class MainClass {

    private static ConsoleUtil consoleUtil;
    private static AreaCalculator areaCalculator;

    public static void main(String[] args) {
        init();

        System.out.printf("Area of figure between 2 functions %n\ty=x^2%n\ty=1/x%nArea = %.4f%n", areaCalculator.countArea());

    }

    private static void init() {
        consoleUtil = new ConsoleUtil();
        areaCalculator = new AreaCalculator();
    }
}
