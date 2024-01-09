import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

class Student {
    public String name;
    public String gender;
    public String course;
    public String module;
    public String id;

    Student() {
        this.name = "";
        this.gender = "";
        this.course = "";
        this.module = "";
        this.id = "";
    }

    Student(String name, String gender, String course, String module, String id) {
        this.name = name;
        this.gender = gender;
        this.course = course;
        this.module = module;
        this.id = id;
    }

    Boolean isValid() {
        return true;
    }

    @Override
    public String toString() {
        return String.format("ID: %s:\nName: %s\nGender: %s\nCourse: %s\nModule: %s", id, name, gender, course, module);
    }
}

class Main {
    public static void main(String[] args) {
        mainMenu(new ArrayList<>());
        // creates a new arraylist in the function mainMenu.
        // Student customStudent = new Student("Tom Sammon", "Male", "Computer Science",
        // "Intro to Functional programming", "ts445");
        // creates a custom student already in the database to query.
    }

    public static void mainMenu(List<Student> students) {
        // creates an array called "Student" with the object "student".
        boolean quit = false;
        Scanner scanner = new Scanner(System.in);

        while (!quit) {
            System.out.println(
                    "Press A to add a new student:\n" + //
                            "Press Q to query an existing student: \n" + //
                            "Press R to remove a new student: \n" + //
                            "Press L to query and existing student by the first letter of each name: \n" + //
                            "Press X to quit.");

            String option = scanner.nextLine();
            System.out.println("You would like to " + option);

            switch (option.toUpperCase()) {
                // looks for the result of option in the switch statement.
                case "A":
                    students.add(addStudent(scanner));
                    // adds the result of addstudent to the array Student.
                    break;
                case "R":
                    removeStudent(scanner, students);
                    // passes through students into the function removeStudent.
                    break;
                case "Q":
                    queryStudent(scanner, students);
                    // passes through students into the function queryStudent.
                    break;
                case "L":
                    QueryStudentByLetter(scanner, students);
                    // passes through students into the function queryStudent.
                    break;
                case "X":
                    quit = true;
                    // Quits the menu
                    break;
                default:
                    // code block
            }
        }
        scanner.close();
    }

    public static Student addStudent(Scanner scanner) {
        System.out.println("Please type in the students name...");
        String name = scanner.nextLine();
        System.out.println("Please type in the students gender...");
        String gender = scanner.nextLine();
        System.out.println("Please type in the students course...");
        String course = scanner.nextLine();
        System.out.println("Please type in the students current module...");
        String module = scanner.nextLine();
        System.out.println("Please type in the students id...");
        String id = scanner.nextLine();

        return new Student(name, gender, course, module, id);
    }

    public static void removeStudent(Scanner scanner, List<Student> students) {

        System.out.println("Please type in the id of the student you would like to remove... ");
        String idToRemove = scanner.nextLine();

        students.removeIf(student -> student.id.equals(idToRemove));
        // removes the scanner "idtoRemove" from the list"
    }

    public static void queryStudent(Scanner scanner, List<Student> students) {

        System.out.println("Select how you would like to search, 1:name , 2:gender , 3:course , 4:module , 5:id ");
        String searchType = scanner.nextLine();

        System.out.println("Please type in the value you would like to query... ");
        String searchValue = scanner.nextLine();

        Predicate<Student> condition = getCondition(searchType, searchValue);
        students.stream()
                .filter(condition)
                .forEach(Main::displayStudent);

    }

    private static Predicate<Student> getCondition(String searchType, String searchValue) {
        switch (searchType) {
            case "1":
                return student -> student.name.equals(searchValue);
            case "2":
                return student -> student.gender.equals(searchValue);
            case "3":
                return student -> student.course.equals(searchValue);
            case "4":
                return student -> student.module.equals(searchValue);
            case "5":
                return student -> student.id.equals(searchValue);
            default:
                return student -> false;
        }
    }

    public static void QueryStudentByLetter(Scanner scanner, List<Student> students) {
        System.out.println("What letter would you like to query ...");
        String LetterChoice = scanner.nextLine();
        // creates an input called letter choice

        students.stream()
                .filter(student -> student.name.startsWith(LetterChoice.toUpperCase())
                        || student.name.startsWith(LetterChoice.toLowerCase()))
                // filters through the list student for results similar to letterChoice.
                .forEach(Main::displayStudent);
        // sends each result to the function displayStudent.

    }

    public static void displayStudent(Student student) {
        System.out.println(student);
        // function for displaying the results to the console
    }
}
