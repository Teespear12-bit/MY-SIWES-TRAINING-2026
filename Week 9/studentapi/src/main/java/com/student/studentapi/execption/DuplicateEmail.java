package com.student.studentapi.execption;

public class DuplicateEmail extends RuntimeException {
    public  DuplicateEmail(String email) {
        super("Error: Email Already exists");
        
        
    }
}
