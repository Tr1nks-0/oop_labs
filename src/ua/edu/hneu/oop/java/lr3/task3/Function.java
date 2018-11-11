package ua.edu.hneu.oop.java.lr3.task3;

import java.util.Map;

public abstract class Function {
    public abstract double calculate(double x) throws ArithmeticException;

    public abstract Map<String, Double> getParameters();
}
