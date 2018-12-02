package ua.edu.hneu.oop.java.lr5.task3;

import java.util.ArrayList;
import java.util.List;

public class MainClass {
    public static void main(String[] args) throws InterruptedException {
        List<Printer> printers = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            printers.add(new NoSynkPrinter(i + 1));
        }
        System.out.println("Not synchronized out:" + System.lineSeparator() + System.lineSeparator());
        printers.forEach(Thread::start);
        for (Printer printer : printers) {
            printer.join();
        }
        printers.clear();


        System.out.println(System.lineSeparator() + System.lineSeparator());
        final Object monitor = "This is a monitor for synk - immutable object";
        for (int i = 0; i < 10; i++) {
            printers.add(new SynkPrinter(i + 1, monitor));
        }
        System.out.println("Synchronized out:" + System.lineSeparator() + System.lineSeparator());
        printers.forEach(Thread::start);
        for (Printer printer : printers) {
            printer.join();
        }
    }
}
