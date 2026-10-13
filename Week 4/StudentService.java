package com.student;

import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private List<Student> students = new ArrayList<>();
    private int nextId = 1;

    public void addStudent(String name, int age, String course, Department department) {
        Student student = new Student(nextId, name, age, course, department);
        students.add(student);
        System.out.println("Student added successfully with ID: " + nextId);
        nextId++;
    }

    public void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No records found.");
            return;
        }

        System.out.println("\n--- Registered Students ---");
        for (Student s : students) {
            s.displayStudent();
        }
    }

    public void searchStudentByName(String name) {
        boolean matchesFound = false;

        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                s.displayStudent();
                matchesFound = true;
            }
        }

        if (!matchesFound) {
            System.out.println("No record matches student name: " + name);
        }
    }

    public void updateStudentDetails(int id, String newName, int newAge, String newCourse, Department newDept) {
        Student s = findById(id);
        if (s != null) {
            s.setName(newName);
            s.setAge(newAge);
            s.setCourse(newCourse);
            s.setDepartment(newDept);
            System.out.println("Student details updated successfully.");
        } else {
            System.out.println("Error: Student with ID " + id + " not found.");
        }
    }

    public void deleteStudentRecord(int id) {
        Student s = findById(id);
        if (s != null) {
            students.remove(s);
            System.out.println("Student record deleted successfully.");
        } else {
            System.out.println("Error: Student with ID " + id + " not found.");
        }
    }

    private Student findById(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }
}

