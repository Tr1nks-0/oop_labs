package ua.edu.hneu.oop.java.util;

import java.io.*;

public class SeriallizeUtil {
    public void serialize(Object object, File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        serialize(object, fileOutputStream);
        fileOutputStream.close();
    }

    public void serialize(Object object, OutputStream outputStream) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
    }

    public Object restore(File file) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        Object serializable = restore(fileInputStream);
        fileInputStream.close();
        return serializable;
    }

    private Object restore(InputStream inputStream) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        return objectInputStream.readObject();
    }
}
