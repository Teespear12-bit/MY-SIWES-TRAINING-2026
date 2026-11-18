import java.util.ArrayList;
import java.util.Scanner;

public class StudentManager {

    static ArrayList<Student> students = new ArrayList<>();
    static int nextId = 1;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n=== STUDENT MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit\n");
            System.out.print("Enter a number (1-6): ");

            String choice = scanner.nextLine().trim();

            if (choice.equals("6")) {
                System.out.println("Exiting system. Goodbye!");
                running = false;
            } else if (choice.equals("1")) {
                addStudent();
            } else if (choice.equals("2")) {
                viewStudents();
            } else if (choice.equals("3")) {
                searchStudent();
            } else if (choice.equals("4")) {
                updateStudent();
            } else if (choice.equals("5")) {
                deleteStudent();
            } else {
                System.out.println("Invalid option! Please enter a number from 1 to 6.");
            }
        }

        scanner.close();
    }

    // 1. ADD STUDENT
    public static void addStudent() {
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter Student Age: ");
        String age = scanner.nextLine().trim();

        System.out.print("Enter Student Department: ");
        String department = scanner.nextLine().trim();

        if (name.equals("") || age.equals("") || department.equals("")) {
            System.out.println("Please fill in all details!");
        } else {
            Student newStudent = new Student(nextId, name, age, department);
            students.add(newStudent);
            nextId = nextId + 1;
            System.out.println("Student added successfully with ID #" + newStudent.id);
        }
    }

    // 2. VIEW STUDENTS
    public static void viewStudents() {
        if (students.size() == 0) {
            System.out.println("No students in the list yet.");
        } else {
            System.out.println("\nALL STUDENTS:\n");
            for (int i = 0; i < students.size(); i++) {
                Student s = students.get(i);
                System.out.println("ID #" + s.id + " - " + s.name + " | Age: " + s.age + " | Department: " + s.department + " |");
            }
        }
    }

    // 3. SEARCH STUDENT
    public static void searchStudent() {
        System.out.print("Enter Name or Department to search: ");
        String search = scanner.nextLine().trim().toLowerCase();

        String foundText = "\nSEARCH RESULTS:\n\n";
        boolean found = false;

        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            boolean nameMatch = s.name.toLowerCase().contains(search);
            boolean departmentMatch = s.department.toLowerCase().contains(search);

            if (nameMatch || departmentMatch) {
                foundText += "ID #" + s.id + " - " + s.name + " (" + s.department + ")\n";
                found = true;
            }
        }

        if (found) {
            System.out.println(foundText);
        } else {
            System.out.println("No matching student found.");
        }
    }

    // 4. UPDATE STUDENT
    public static void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        int updateId = Integer.parseInt(scanner.nextLine().trim());
        boolean updated = false;

        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            if (s.id == updateId) {
                System.out.print("Enter New Name (" + s.name + "): ");
                String newName = scanner.nextLine().trim();

                System.out.print("Enter New Age (" + s.age + "): ");
                String newAge = scanner.nextLine().trim();

                System.out.print("Enter New Department (" + s.department + "): ");
                String newDepartment = scanner.nextLine().trim();

                s.name = newName;
                s.age = newAge;
                s.department = newDepartment;

                System.out.println("Student with ID #" + updateId + " was updated successfully!");
                updated = true;
                break;
            }
        }

        if (!updated) {
            System.out.println("Student with ID #" + updateId + " was not found.");
        }
    }

    // 5. DELETE STUDENT
    public static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        int deleteId = Integer.parseInt(scanner.nextLine().trim());
        boolean deleted = false;

        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            if (s.id == deleteId) {
                students.remove(i);
                System.out.println("Student with ID #" + deleteId + " has been deleted successfully!");
                deleted = true;
                break;
            }
        }

        if (!deleted) {
            System.out.println("Student with ID #" + deleteId + " was not found.");
        }
    }
}