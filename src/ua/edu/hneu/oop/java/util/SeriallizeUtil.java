package ua.edu.hneu.oop.java.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class SeriallizeUtil {
    public void serializeXml(Object object, File file) throws IOException, JAXBException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        serializeXml(object, fileOutputStream);
        fileOutputStream.close();
    }

    public void serializeXml(Object object, OutputStream outputStream) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(object, outputStream);
    }

    public <T> T restoreXML(File file, Class<T> clazz) throws IOException, ClassNotFoundException, JAXBException {
        FileInputStream fileInputStream = new FileInputStream(file);
        T object = restoreXML(fileInputStream, clazz);
        fileInputStream.close();
        return object;
    }

    public <T> T restoreXML(InputStream inputStream, Class<T> clazz) throws IOException, ClassNotFoundException, JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (T) unmarshaller.unmarshal(inputStream);
    }


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
