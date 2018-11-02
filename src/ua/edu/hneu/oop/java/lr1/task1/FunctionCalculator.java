package ua.edu.hneu.oop.java.lr1.task1;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


public class FunctionCalculator {
    private static final Map<Map.Entry<Double, Double>, Function<Double, Double>> functionIntervals = new HashMap<>();
    public static final double MIN = -10.0;
    public static final double MAX = 4.0;

    /*
     *   [-10 ; -6] : y = -sqrt(-(x*x) - 16x - 60) + 2
     *   [-6  ; -4] : y=2
     *   [-4  ;  2) : y=-0.5x
     *   [ 2  ;  4) : y=x-3
     */
    static {
        functionIntervals.put(new AbstractMap.SimpleEntry<>(-10.0, -6.0),
                x -> -1 * Math.sqrt(-1 * ((x * x) + 16 * x + 60)) + 2);

        functionIntervals.put(new AbstractMap.SimpleEntry<>(-6.0, -4.0),
                x -> 2.0);

        functionIntervals.put(new AbstractMap.SimpleEntry<>(-4.0, 2.0),
                x -> -0.5 * x);

        functionIntervals.put(new AbstractMap.SimpleEntry<>(2.0, 4.0),
                x -> -3.0 * x);
    }

    public boolean isInRange(double x) {
        return MIN <= x && x <= MAX;
    }

    public double calculate(double x) throws IllegalArgumentException {
        return functionIntervals.entrySet().stream()
                .filter(e -> e.getKey().getKey() <= x && x <= e.getKey().getValue())
                .findFirst().orElseThrow(() -> new IllegalArgumentException("x value is out of range"))
                .getValue().apply(x);
    }
}
