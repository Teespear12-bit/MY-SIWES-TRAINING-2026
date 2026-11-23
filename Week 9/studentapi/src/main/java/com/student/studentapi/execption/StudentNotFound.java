package com.student.studentapi.execption;

public class StudentNotFound extends RuntimeException {

    public StudentNotFound(Integer id) {
        super("No student with id " + id + " was found");
    }
}