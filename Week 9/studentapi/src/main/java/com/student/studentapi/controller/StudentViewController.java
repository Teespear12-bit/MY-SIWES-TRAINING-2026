package com.student.studentapi.controller;

import com.student.studentapi.dto.CreateStudentRequest;
import com.student.studentapi.execption.DuplicateEmail;
import com.student.studentapi.execption.StudentNotFound;
import com.student.studentapi.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/studentMG")
public class StudentViewController {

    private final StudentService studentService;

    public StudentViewController(StudentService studentService) {
        this.studentService = studentService;
    }

    // The menu page: /studentMG
    @GetMapping
    public String showMenu() {
        return "operations";
    }

    // The list page: /studentMG/students
    @GetMapping("/students")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "viewstudents";
    }

    // Shows the empty add form: /studentMG/students/new
    @GetMapping("/students/new")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new CreateStudentRequest());
        return "addstudents";
    }

    // Receives the filled form and stays on the same page
    @PostMapping("/students/new")
    public String createStudent(@Valid @ModelAttribute("student") CreateStudentRequest request,
                                BindingResult result,
                                Model model) {

        // 1. The rules in CreateStudentRequest ran because of @Valid.
        //    If any failed, stay on the form and show the messages.
        if (result.hasErrors()) {
            return "addstudents";
        }

        // 2. Only the service and database know these two problems
        try {
            studentService.createStudent(request);
        } catch (DuplicateEmail ex) {
            result.rejectValue("email", "duplicate", "A student with this email already exists");
            return "addstudents";
        } catch (DataIntegrityViolationException ex) {
            result.rejectValue("departmentId", "missing", "That department does not exist");
            return "addstudents";
        }

        // 3. Saved: stay on this page, show the message, and clear the form
        model.addAttribute("successMessage", "Student created successfully!");
        model.addAttribute("student", new CreateStudentRequest());
        return "addstudents";
    }

    // Delete, then show the list again with a message (no redirect)
    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Integer id, Model model) {
        try {
            studentService.deleteStudent(id);
            model.addAttribute("successMessage", "Student deleted successfully!");
        } catch (StudentNotFound ex) {
            model.addAttribute("errorMessage", "Student with id " + id + " does not exist");
        }

        // Reload the list so the table is up to date, then show the same list page
        model.addAttribute("students", studentService.getAllStudents());
        return "viewstudents";
    }
}