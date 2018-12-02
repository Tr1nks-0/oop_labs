package ua.edu.hneu.oop.java.lr5.task4;

import ua.edu.hneu.oop.java.lr3.task2.RegexWorker;
import ua.edu.hneu.oop.java.util.SeriallizeUtil;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

public class MainClass {
    private static SeriallizeUtil seriallizeUtil = new SeriallizeUtil();

    public static void main(String[] args) throws IOException, ClassNotFoundException, JAXBException {
        List<RegexWorker> workers = obtainWorkers();

        boolean soapEquals = checkSerialize(workers);
        System.out.printf("SOAP serialized == non serialized -- %b%n", soapEquals);

        boolean xmlEquals = checkSerializeXML(workers);
        System.out.printf("XML serialized == non serialized -- %b%n", xmlEquals);
    }

    public static boolean checkSerializeXML(List<RegexWorker> workers) throws IOException, ClassNotFoundException, JAXBException {
        File file = File.createTempFile("serialize-test", "temp");
        file.deleteOnExit();
        seriallizeUtil.serializeXml(new RegexWorkerListXMLWrapper(workers), file);
        List<RegexWorker> workersRestored = seriallizeUtil.restoreXML(file, RegexWorkerListXMLWrapper.class).getWorkers();
        AtomicBoolean equals = new AtomicBoolean(true);
        workersRestored.forEach(regexWorker -> equals.set(equals.get() && workers.contains(regexWorker)));
        return equals.get();
    }

    public static boolean checkSerialize(List<RegexWorker> workers) throws IOException, ClassNotFoundException {

        File file = File.createTempFile("serialize-test", "temp");
        file.deleteOnExit();
        seriallizeUtil.serialize(workers, file);
        List<RegexWorker> workersRestored = (List<RegexWorker>) seriallizeUtil.restore(file);
        AtomicBoolean equals = new AtomicBoolean(true);
        workersRestored.forEach(regexWorker -> equals.set(equals.get() && workers.contains(regexWorker)));
        return equals.get();
    }

    private static List<RegexWorker> obtainWorkers() {
        List<RegexWorker> workers = new ArrayList<>();
        workers.add(obtainRegexWorker("(?iu)\\w{2,5}", "q qw qwe qwer qwert qwerty qwertyu qwertyui"));
        workers.add(obtainRegexWorker("(?iu)\\d?", "1 12 1 22 3 2 23 45 32 2 1"));
        workers.add(obtainRegexWorker("(?iu).{2,}", "q w e q w we qwe qw re t yu qwes qaz wedd"));
        return workers;
    }

    private static RegexWorker obtainRegexWorker(String pattern, String text) {
        RegexWorker regexWorker = new RegexWorker();
        regexWorker.setPattern(Pattern.compile(pattern));
        regexWorker.setText(text);
        return regexWorker;
    }

    @XmlRootElement()
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class RegexWorkerListXMLWrapper {
        @XmlElementWrapper
        private List<RegexWorker> workers;

        public RegexWorkerListXMLWrapper() {
        }

        public RegexWorkerListXMLWrapper(List<RegexWorker> workers) {
            this.workers = workers;
        }

        public List<RegexWorker> getWorkers() {
            return workers;
        }

        public void setWorkers(List<RegexWorker> workers) {
            this.workers = workers;
        }
    }

}
