package ua.edu.hneu.oop.java.lr1.task2;

import java.util.function.Function;

public class AreaCalculator {
    private static final double DX = 10e-5;
    private static final double MIN_RANGE_BOUND = 1;
    private static final double MAX_RANGE_BOUND = 2;
    private static final Function<Double, Double> UPPER_FUUNCTION = x -> x * x;
    private static final Function<Double, Double> LOWER_FUUNCTION = x -> 1 / x;

    public double countArea() {
        double x = MIN_RANGE_BOUND + DX;
        double f1Value = UPPER_FUUNCTION.apply(x);
        double f2Value = LOWER_FUUNCTION.apply(x);
        double area = 0.0;
        while (f1Value > f2Value && x <= MAX_RANGE_BOUND) {
            area += (f1Value - f2Value) * DX;
            x += DX;
            f1Value = UPPER_FUUNCTION.apply(x);
            f2Value = LOWER_FUUNCTION.apply(x);
        }
        return area;
    }
}
