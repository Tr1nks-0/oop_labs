package ua.edu.hneu.oop.java.lr6.task2;

public abstract class ProductCreator {
    private Product product;

    public abstract Product factoryMethod();

    public void operation() {
        product = factoryMethod();
    }
}
