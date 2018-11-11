package ua.edu.hneu.oop.java.lr3.task3;

import java.util.HashMap;
import java.util.Map;

public class Kub extends Function {
    private double a, b, c;

    public Kub(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double calculate(double x) {
        return a * x * x + b * x + c;
    }

    @Override
    public Map<String, Double> getParameters() {
        Map<String, Double> params = new HashMap<>();
        params.put("a", a);
        params.put("b", b);
        params.put("c", c);
        return params;
    }

    @Override
    public String toString() {
        return "a*x*x + b*x + c";
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }
}
