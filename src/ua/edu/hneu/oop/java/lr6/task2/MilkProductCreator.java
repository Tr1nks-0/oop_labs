package ua.edu.hneu.oop.java.lr6.task2;

public class MilkProductCreator extends ProductCreator {
    @Override
    public Product factoryMethod()
    {
        return new MilkProduct();
    }
}
