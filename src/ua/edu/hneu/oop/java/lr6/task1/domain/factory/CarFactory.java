package ua.edu.hneu.oop.java.lr6.task1.domain.factory;

import ua.edu.hneu.oop.java.lr6.task1.domain.entity.AbstractCar;
import ua.edu.hneu.oop.java.lr6.task1.domain.entity.AbstractEngine;

public abstract class CarFactory {
    public abstract AbstractCar createCar();

    public abstract AbstractEngine createEngine();
}
