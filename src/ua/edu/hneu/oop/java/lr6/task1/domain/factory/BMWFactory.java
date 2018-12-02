package ua.edu.hneu.oop.java.lr6.task1.domain.factory;

import ua.edu.hneu.oop.java.lr6.task1.domain.entity.AbstractCar;
import ua.edu.hneu.oop.java.lr6.task1.domain.entity.AbstractEngine;
import ua.edu.hneu.oop.java.lr6.task1.domain.entity.BMWCar;
import ua.edu.hneu.oop.java.lr6.task1.domain.entity.BMWEngine;

public class BMWFactory extends CarFactory {
    @Override
    public AbstractCar createCar() {
        return new BMWCar();
    }

    @Override
    public AbstractEngine createEngine() {
        return new BMWEngine();
    }
}
