package com.week6.week6.service;

import com.week6.week6.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    public List<Student> getAllStudents() {
        return List.of(
            new Student(1, "Chidi Okafor", 21, "Computer Science"),
            new Student(2, "Amina Bello", 23, "Mathematics"),
            new Student(3, "Tunde Adeyemi", 20, "Physics")
        );
    }
}