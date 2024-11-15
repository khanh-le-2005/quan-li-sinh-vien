import java.util.LinkedList;
import java.util.Scanner;
public class studentManager {
    private LinkedList<student> students = new LinkedList<>();
    private Scanner scanner = new Scanner(System.in);
    public void addStudent() {
        try {
            int id = getValidStudentId();
            String name = getValidStudentName();
            double marks = getValidMarks();

            students.add(new student(id, name, marks));
            System.out.println("Student added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }
    public void editStudent() {
        int id = getValidStudentId();
        student student = findStudentById(id);
        if (student != null) {
            System.out.println("Editing student: " + student);
            String name = getValidStudentName();
            double marks = getValidMarks();

            student.setName(name);
            student.setMarks(marks);
            student.updateRank();

            System.out.println("Student updated: " + student);
        } else {
            System.out.println("Student not found with ID: " + id);
        }
    }
    public void deleteStudent() {
        int id = getValidStudentId();

        student student = findStudentById(id);
        if (student != null) {
            students.remove(student);
            System.out.println("Student deleted: " + student);
        } else {
            System.out.println("Student not found with ID: " + id);
        }
    }
    public void searchStudentById() {
        int id = getValidStudentId();

        student student = findStudentById(id);
        if (student != null) {
            System.out.println("Student found: " + student);
        } else {
            System.out.println("Student not found with ID: " + id);
        }
    }
    public void searchStudentByName() {
        System.out.print("Enter Student Name to search: ");
        String name = scanner.nextLine().trim();

        boolean found = false;
        for (student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                System.out.println("Student found: " + student);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No student found with name: " + name);
        }
    }
    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students in the system.");
        } else {
            students.forEach(System.out::println);
        }
    }
    public void sortStudentsByMarks() {
        students.sort((s1, s2) -> Double.compare(s2.getMarks(), s1.getMarks()));
        System.out.println("Students sorted by marks:");
        displayStudents();
    }
    private student findStudentById(int id) {
        for (student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }
    private int getValidStudentId() {
        int id = 0;
        while (true) {
            System.out.print("Enter Student ID: ");
            if (scanner.hasNextInt()) {
                id = scanner.nextInt();
                scanner.nextLine();
                return id;
            } else {
                System.out.println("Invalid input! Please enter a numeric ID.");
                scanner.nextLine();
            }
        }
    }
    private String getValidStudentName() {
        String name;
        while (true) {
            System.out.print("Enter Student Name: ");
            name = scanner.nextLine().trim();
            if (name.matches("[a-zA-Z ]+")) {
                return name;
            } else {
                System.out.println("Invalid input! Please enter a valid name containing only letters and spaces.");
            }
        }
    }
    private double getValidMarks() {
        double marks = 0;
        while (true) {
            System.out.print("Enter Student Marks (0-10): ");
            if (scanner.hasNextDouble()) {
                marks = scanner.nextDouble();
                scanner.nextLine();
                if (marks >= 0 && marks <= 10) {
                    return marks;
                } else {
                    System.out.println("Invalid input! Marks should be between 0 and 10.");
                }
            } else {
                System.out.println("Invalid input! Please enter numeric values for Marks.");
                scanner.nextLine();
            }
        }
    }
}
