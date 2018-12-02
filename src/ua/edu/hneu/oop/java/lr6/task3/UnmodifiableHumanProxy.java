package ua.edu.hneu.oop.java.lr6.task3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UnmodifiableHumanProxy<T extends Human> implements InvocationHandler {
    private T component;

    public UnmodifiableHumanProxy(T component) {
        this.component = component;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().startsWith("set")) {
            System.out.println("For unmodifiable proxy this operation is not allowed");
            return null;
        }
        return method.invoke(component, args);
    }

}
