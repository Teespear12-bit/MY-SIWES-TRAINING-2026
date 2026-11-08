package com.student.studentapi.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CreateStudentRequest {

    @NotBlank(message = "Enter firts name")
    @Size(max = 40, message = "First name can not be more than 40 characters")
    @Size (min = 2, message = "First name should be atleast two characters long")
    private String firstName;
    
    @NotBlank(message = "Enter last name")
    @Size (max = 40, message = "Last name can not be more than 40 characters")
    @Size (min = 2, message = "Last name should be atleast two characters long")
    private String lastName;

    @NotBlank(message = "Enter your Email")
    @Email(message = "Email must be a valid email address")
    @Size(max = 100, message = "Email must not be more than 100 characters")
    private String email;
    
    @NotBlank(message = "What level are you in?")
    @Size (min = 3, message = "Invalid level")
    @Size(max = 10 , message = "Invalid level")
    private String level;

    @Positive(message = "Error, Dept ID can't be Negative")
    private Integer departmentId;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    
}