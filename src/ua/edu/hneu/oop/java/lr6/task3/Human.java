package ua.edu.hneu.oop.java.lr6.task3;

import java.lang.reflect.Proxy;

public interface Human {

    String getName();

    void setName(String name);

    default <T extends Human> T getUnmodifiable() {
        return (T) Proxy.newProxyInstance(Human.class.getClassLoader(), new Class[]{Human.class}, new UnmodifiableHumanProxy<>(this));
    }
}
