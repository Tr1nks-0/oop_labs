package ua.edu.hneu.oop.java.lr6.task2;

public class MainClass {
    public static void main(String[] args) {
        ProductCreator productCreator = new MilkProductCreator();
        productCreator.factoryMethod();
        productCreator.operation();
    }
}
