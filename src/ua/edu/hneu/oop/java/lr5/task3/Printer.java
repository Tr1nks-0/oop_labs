package ua.edu.hneu.oop.java.lr5.task3;

import java.util.Random;

public abstract class Printer extends Thread {
    private static final String FORMAT = "printer %-2d  delay %-4d ms   № %2d%n";
    private static final int MIN_TIME_DELAY = 100;
    private static final int MAX_TIME_DELAY = 1000;
    private int number;
    private Random random;

    public Printer(int number) {
        this.number = number;
        this.random = new Random(System.currentTimeMillis());
    }

    public void print() {
        long delay;
        for (int i = 0; i < 10; i++) {
            delay = obtainSleepTime();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf(FORMAT, number, delay, i + 1);
        }
    }

    private long obtainSleepTime() {
        return random.nextInt((MAX_TIME_DELAY - MIN_TIME_DELAY) + 1) + MIN_TIME_DELAY;
    }
}
