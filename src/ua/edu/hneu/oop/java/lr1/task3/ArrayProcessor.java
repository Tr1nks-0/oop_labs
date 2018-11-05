package ua.edu.hneu.oop.java.lr1.task3;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Random;

public class ArrayProcessor {
    private static final int MAX_ARRAY_ELEMENT = 100;
    private static final int MIN_ARRAY_ELEMENT = -100;
    private Random random;

    public ArrayProcessor() {
        this.random = new Random(System.currentTimeMillis());
    }

    public int[] obtainArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(((MAX_ARRAY_ELEMENT-MIN_ARRAY_ELEMENT)+1)) + MIN_ARRAY_ELEMENT;
        }
        return array;
    }

    public Map.Entry<Integer, Integer> processArray(int[] array) {
        int min = Integer.MAX_VALUE;
        int sum = 0;
        boolean needSum = false;
        for (int i : array) {
            if (i < min) {
                min = i;
            }
            if (needSum) {
                sum += Math.abs(i);
            } else if (i == 0) {
                needSum = true;
            }
        }
        return new AbstractMap.SimpleEntry<>(min, sum);
    }

    public int[] reorder(int[] array) {
        int[] out = new int[array.length];
        int index = 0;
        for (int i = 0; i < array.length; i += 2, index++) {
            out[index] = array[i];
        }
        for (int i = 1; i < array.length; i += 2, index++) {
            out[index] = array[i];
        }
        return out;
    }
}
