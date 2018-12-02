package ua.edu.hneu.oop.java.lr5.task4;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.regex.Pattern;

public class PatternXmlAdapter extends XmlAdapter<String, Pattern> {
    @Override
    public String marshal(Pattern v) {
        return v.pattern();
    }

    @Override
    public Pattern unmarshal(String v) {
        return Pattern.compile(v);
    }
}
