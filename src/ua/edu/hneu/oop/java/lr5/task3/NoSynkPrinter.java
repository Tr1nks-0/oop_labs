package ua.edu.hneu.oop.java.lr5.task3;

public class NoSynkPrinter extends Printer {

    public NoSynkPrinter(int number) {
        super(number);
    }

    @Override
    public void run() {
        print();
    }
}
