package ua.edu.hneu.oop.java.lr3.task2;

import ua.edu.hneu.oop.java.lr5.task4.PatternXmlAdapter;
import ua.edu.hneu.oop.java.util.ConsoleUtil;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
public class RegexWorker implements Serializable {
    private static final long serialVersionUID = 1L;
    @XmlElement
    @XmlJavaTypeAdapter(PatternXmlAdapter.class)
    private Pattern pattern;
    @XmlElement
    private String text;

    public boolean contains() {
        return pattern.matcher(text).find();
    }

    public void printAllMatches() {
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            ConsoleUtil.getInstance().println(matcher.group(1));
        }
    }

    public void removeAllMatches() {
        Matcher matcher = pattern.matcher(text);
        text = matcher.replaceAll("");
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RegexWorker that = (RegexWorker) o;
        return Objects.equals(text, that.text) &&
                (Objects.isNull(pattern) && Objects.isNull(that.pattern) ||
                        pattern.pattern().equals(that.pattern.pattern()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(pattern, text);
    }
}
