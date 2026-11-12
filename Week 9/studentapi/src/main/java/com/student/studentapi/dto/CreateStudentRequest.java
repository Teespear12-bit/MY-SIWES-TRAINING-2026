package com.student.studentapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CreateStudentRequest {

    @NotBlank(message = "Enter first name")
    @Size(min = 2, max = 40, message = "First name must be between 2 and 40 characters")
    @Pattern(regexp = "[A-Za-z' -]+", message = "Invalid name: use letters only")
    private String firstName;

    @NotBlank(message = "Enter last name")
    @Size(min = 2, max = 40, message = "Last name must be between 2 and 40 characters")
    @Pattern(regexp = "[A-Za-z' -]+", message = "Invalid name: use letters only")
    private String lastName;

    @NotBlank(message = "Enter your email")
    @Email(message = "Email must be a valid email address")
    @Size(max = 100, message = "Email must not be more than 100 characters")
    private String email;

    @NotBlank(message = "What level are you in?")
    @Pattern(regexp = "100LVL|200LVL|300LVL|400LVL|500LVL",
             message = "Level must be 100LVL, 200LVL, 300LVL, 400LVL or 500LVL")
    private String level;

    @Positive(message = "Department ID must be a positive number")
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