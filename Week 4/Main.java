package com.student;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static StudentService studentService = new StudentService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exitFlag = false;

        while (!exitFlag) {
            displayMenu();
            int userSelection = readIntegerInput("Select an option: ");

            if (userSelection == 1) {
                handleAddStudent();
            } else if (userSelection == 2) {
                studentService.viewAllStudents();
            } else if (userSelection == 3) {
                handleSearchStudent();
            } else if (userSelection == 4) {
                handleUpdateStudent();
            } else if (userSelection == 5) {
                handleDeleteStudent();
            } else if (userSelection == 6) {
                System.out.println("Exiting Console Student Manager. Goodbye!");
                exitFlag = true;
            } else {
                System.out.println("Invalid selection. Pick an option between 1 and 6.");
            }
            System.out.println();
        }
    }

    private static void displayMenu() {
        System.out.println("==== STUDENT MANAGER (OOP) ====");
        System.out.println("1. Add Student");
        System.out.println("2. View Students");
        System.out.println("3. Search Student");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Exit");
    }

    private static void handleAddStudent() {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        int age = readIntegerInput("Enter Age: ");

        System.out.print("Enter Course: ");
        String course = scanner.nextLine();

        int deptId = readIntegerInput("Enter Department ID: ");
        System.out.print("Enter Department Name: ");
        String deptName = scanner.nextLine();

        Department dept = new Department(deptId, deptName);
        studentService.addStudent(name, age, course, dept);
    }

    private static void handleSearchStudent() {
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();
        studentService.searchStudentByName(name);
    }

    private static void handleUpdateStudent() {
        int id = readIntegerInput("Enter ID of student to update: ");

        System.out.print("Enter updated Name: ");
        String name = scanner.nextLine();

        int age = readIntegerInput("Enter updated Age: ");

        System.out.print("Enter updated Course: ");
        String course = scanner.nextLine();

        int deptId = readIntegerInput("Enter updated Dept ID: ");
        System.out.print("Enter updated Dept Name: ");
        String deptName = scanner.nextLine();

        Department dept = new Department(deptId, deptName);
        studentService.updateStudentDetails(id, name, age, course, dept);
    }

    private static void handleDeleteStudent() {
        int id = readIntegerInput("Enter ID of student to remove: ");
        studentService.deleteStudentRecord(id);
    }

    private static int readIntegerInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }
}