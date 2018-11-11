package ua.edu.hneu.oop.java.lr3.task4;

import java.util.Map;

public interface Function {
    double calculate(double x) throws ArithmeticException;

    Map<String, Double> getParameters();
}
