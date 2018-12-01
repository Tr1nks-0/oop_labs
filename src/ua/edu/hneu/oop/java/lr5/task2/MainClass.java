package ua.edu.hneu.oop.java.lr5.task2;

import ua.edu.hneu.oop.java.util.ConsoleUtil;
import ua.edu.hneu.oop.java.util.SeriallizeUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public class MainClass {
    private static final String IO_EXCEPTION_FORMAT = "Something went wrong with obtaining value. Original error is:\"%s\".%n%n";
    private static final String YES_REGEX = "(?iu)y|yes";

    private static final Comparator<Student> SURNAME_COMPARATOR = Comparator.comparing(Student::getSurname);
    private static final Comparator<Student> GROUP_COMPARATOR = Comparator.comparingInt(Student::getGroup);
    private static final Comparator<Student> AVERAGE_MARK_COMPARATOR = Comparator.comparingDouble(Student::getAverageMark);
    private static final Comparator[] COMPARATORS = {SURNAME_COMPARATOR, GROUP_COMPARATOR, AVERAGE_MARK_COMPARATOR};

    private static final BiPredicate<Student, String> SURNAME_SEARCH = (student, phrase) -> student.getSurname().contains(phrase);
    private static final BiPredicate<Student, String> INITIALS_SEARCH = (student, phrase) -> student.getInitials().contains(phrase);
    private static final BiPredicate<Student, String> GROUP_SEARCH = (student, phrase) -> String.valueOf(student.getGroup()).contains(phrase);
    private static final BiPredicate<Student, String> AVERAGE_MARK_SEARCH = (student, phrase) -> String.valueOf(student.getAverageMark()).contains(phrase);
    private static final BiPredicate[] SEARCHES = {SURNAME_SEARCH, INITIALS_SEARCH, GROUP_SEARCH, AVERAGE_MARK_SEARCH};

    private static List<Student> students;
    private static Menu mainMenu;
    private static ConsoleUtil consoleUtil;
    private static StudentFactory studentFactory;
    private static SeriallizeUtil seriallizeUtil;

    private static boolean run = true;

    public static void main(String[] args) {
        initialize();
        mainMenu.printHelp();
        do {
            try {
                mainMenu.process();
            } catch (IllegalArgumentException e) {
                consoleUtil.println(e.getMessage());
            } catch (IOException | ClassNotFoundException e) {
                consoleUtil.printf(IO_EXCEPTION_FORMAT, e.getMessage());
            }
        } while (run);
    }

    private static void list() {
        consoleUtil.printf("%n");
        if (students.isEmpty()) {
            consoleUtil.println("Students list is empty - nothing to show.");
        } else {
            printStudentsList(students);
        }
        consoleUtil.printf("%n");
    }

    private static void addNewStudent() throws IOException {
        Student student = studentFactory.createStudent();
        students.add(student);
        students.sort(AVERAGE_MARK_COMPARATOR);
        consoleUtil.println("Student successfully was added");
    }

    private static void readStudentsFromFile() throws IOException, ClassNotFoundException {
        consoleUtil.println("Enter path to file");
        String path = consoleUtil.readInput();
        File file = new File(path);
        if (file.exists() && file.canRead()) {
            students = (List<Student>) seriallizeUtil.restore(file);
            consoleUtil.println("Students successfully read from file");
        } else {
            throw new IllegalArgumentException("File \"" + path + "\" is not exists or can't be read.");
        }
    }

    private static void writeStudentsToFile() throws IOException {
        consoleUtil.println("Enter path to file");
        String path = consoleUtil.readInput();
        File file = new File(path);
        if (file.canWrite()) {
            seriallizeUtil.serialize(students, file);
            consoleUtil.println("Students successfully wrote to file");
        } else {
            throw new IllegalArgumentException("File \"" + path + "\" can't be write.");
        }
    }

    private static void sort() throws IOException {
        consoleUtil.printf("To sort list enter number of row to sort%n\t1 - surname%n\t2 - group%n\t3 - average mark%n");
        String input = consoleUtil.readInput();
        try {
            int index = Integer.parseInt(input);
            if (index < 1 || index > 3) {
                throw new IllegalArgumentException("Entered number is out of range");
            }
            students.sort(COMPARATORS[index - 1]);
            consoleUtil.println("Successfully sorted");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Entered value can not be interpreted as number");
        }
    }


    private static void search() throws IOException {
        consoleUtil.printf("Enter number of row where search%n\t1 - surname%n\t2 - initials%n\t3 - group%n\t4 - average mark%n");
        String input = consoleUtil.readInput();
        try {
            int index = Integer.parseInt(input);
            if (index < 1 || index > 3) {
                throw new IllegalArgumentException("Entered number is out of range");
            }
            consoleUtil.println("Enter phrase to search");
            String phrase = consoleUtil.readInput();

            Optional<Student> result = students.stream().filter(s -> SEARCHES[index - 1].test(s, phrase)).findFirst();

            if (result.isPresent()) {
                consoleUtil.println(result.get().toTableString(0));
            } else {
                consoleUtil.println("Nothing was found");
            }

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Entered value can not be interpreted as number");
        }
    }

    private static void print() {
        List<Student> filtered = students.stream().filter(student -> {
            for (int mark : student.getMarks()) {
                if (mark > 3) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());

        if (filtered.isEmpty()) {
            consoleUtil.println("There are not any student with mark greater than 3");
        } else {
            printStudentsList(filtered);
        }

    }

    private static void delete() throws IOException {
        consoleUtil.println("Enter number of student to be deleted");
        String input = consoleUtil.readInput();
        try {
            int index = Integer.parseInt(input);
            if (index < 0 || index > students.size()) {
                throw new IllegalArgumentException("Entered number is out of range");
            }
            students.remove(index - 1);
            consoleUtil.println("Student successfully was deleted");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Entered value can not be interpreted as number");
        }
    }

    private static void clear() throws IOException {
        consoleUtil.println("This operation lead to erasing all students in current list. Are You sure?(y/n):");
        String input = consoleUtil.readInput();
        if (input.matches(YES_REGEX)) {
            students.clear();
            consoleUtil.println("Students list was successfully cleared");
        }
    }

    private static void exit() {
        run = false;
        consoleUtil.println("Exiting ...");
    }

    private static void initialize() {
        students = new ArrayList<>();
        seriallizeUtil = new SeriallizeUtil();
        consoleUtil = ConsoleUtil.getInstance();
        studentFactory = new StudentFactory(consoleUtil);
        mainMenu = new Menu(consoleUtil);
        mainMenu.addMenuRow("Add new student to current list.", MainClass::addNewStudent, "a", "add");
        mainMenu.addMenuRow("Open existing file.", MainClass::readStudentsFromFile, "o", "open");
        mainMenu.addMenuRow("Print all students .", MainClass::list, "l", "list");
        mainMenu.addMenuRow("Sort current list.", MainClass::sort, "s", "sort");
        mainMenu.addMenuRow("Find by current list.", MainClass::search, "f", "find");
        mainMenu.addMenuRow("Print all students with marks greater than 3.", MainClass::print, "p", "print");
        mainMenu.addMenuRow("Write current list to file.", MainClass::writeStudentsToFile, "w", "write");
        mainMenu.addMenuRow("Delete student from current list.", MainClass::delete, "d", "delete");
        mainMenu.addMenuRow("Clear current list.", MainClass::clear, "c", "clear");
        mainMenu.addMenuRow("Exit.", MainClass::exit, "e", "exit");
    }

    private static void printStudentsList(List<Student> filtered) {
        consoleUtil.print(Student.HEADER);
        for (int i = 0; i < filtered.size(); i++) {
            consoleUtil.print(filtered.get(i).toTableString(i + 1));
        }
    }
}
