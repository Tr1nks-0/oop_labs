package ua.edu.hneu.oop.java.lr5.task3;

public class SynkPrinter extends Printer {
    private Object monitor;

    public SynkPrinter(int number, Object monitor) {
        super(number);
        this.monitor = monitor;
    }

    @Override
    public void run() {
        synchronized (monitor) {
            print();
        }
    }
}
