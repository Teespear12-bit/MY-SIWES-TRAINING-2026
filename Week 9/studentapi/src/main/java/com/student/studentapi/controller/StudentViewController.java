package com.student.studentapi.controller;

import com.student.studentapi.dto.CreateStudentRequest;
import com.student.studentapi.dto.StudentResponse;
import com.student.studentapi.execption.DuplicateEmail;
import com.student.studentapi.execption.StudentNotFound;
import com.student.studentapi.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/studentMG")
public class StudentViewController {

    private final StudentService studentService;

    public StudentViewController(StudentService studentService) {
        this.studentService = studentService;
    }

    // SHOWS MENU PAGE: /studentMG (operations.html)
    @GetMapping
    public String showMenu() {
        return "operations";
    }

    // View All Students: /studentMG/students (viewstudents.html)
    @GetMapping("/students")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "viewstudents";
    }

    //Search Lookup Page: /studentMG/students/search-page (searchstudent.html)
    @GetMapping("/students/search-page")
    public String showSearchPage() {
        return "searchstudent";
    }

    @GetMapping("/students/search")
    public String searchStudentRedirect(@RequestParam(value = "id", required = false) Integer id, RedirectAttributes redirectAttributes) {
        if (id == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please enter a valid Student ID.");
            return "redirect:/studentMG/students/search-page";
        }
        return "redirect:/studentMG/students/" + id;
    }

    //Student Details Page: /studentMG/students/{id} (studentdetails.html)
    @GetMapping("/students/{id}")
    public String viewStudentDetails(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            StudentResponse student = studentService.getStudentById(id);
            model.addAttribute("student", student);
            return "studentdetails";
        } catch (StudentNotFound ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "No student found with ID: " + id);
            return "redirect:/studentMG/students/search-page";
        }
    }

    //Update Lookup Page: /studentMG/students/update-page (updatestudent.html)
    @GetMapping("/students/update-page")
    public String showUpdateLookupPage() {
        return "updatestudent"; 
    }

    @GetMapping("/students/update-search")
    public String updateStudentRedirect(@RequestParam(value = "id", required = false) Integer id, RedirectAttributes redirectAttributes) {
        if (id == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please enter a valid Student ID.");
            return "redirect:/studentMG/students/update-page";
        }
        return "redirect:/studentMG/students/edit/" + id;
    }

    //Delete Lookup Page: /studentMG/students/delete-page (removestudent.html)
    @GetMapping("/students/delete-page")
    public String showDeleteLookupPage() {
        return "removestudent"; 
    }

    @GetMapping("/students/delete-search")
    public String deleteStudentRedirect(@RequestParam(value = "id", required = false) Integer id, RedirectAttributes redirectAttributes) {
        if (id == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please enter a valid Student ID.");
            return "redirect:/studentMG/students/delete-page";
        }
        return "redirect:/studentMG/students/delete/" + id;
    }

    //Add Student Form: /studentMG/students/new  (addstudents.html)
    @GetMapping("/students/new")
    public String showCreateForm(Model model) {
        if (!model.containsAttribute("student")) {
            model.addAttribute("student", new CreateStudentRequest());
        }
        return "addstudents";
    }

    @PostMapping("/students/new")
    public String createStudent(@Valid @ModelAttribute("student") CreateStudentRequest request,
                                BindingResult result,
                                RedirectAttributes redirectAttributes,
                                Model model) {

        if (result.hasErrors()) {
            return "addstudents";
        }

        try {
            studentService.createStudent(request);
            redirectAttributes.addFlashAttribute("successMessage", "Student created successfully!");
            return "redirect:/studentMG/students/new";
        } catch (DuplicateEmail ex) {
            result.rejectValue("email", "duplicate", "A student with this email already exists");
            return "addstudents";
        } catch (DataIntegrityViolationException ex) {
            result.rejectValue("departmentId", "missing", "That department does not exist");
            return "addstudents";
        }
    }

    //Edit Form: /studentMG/students/edit/{id} (editstudentsdet.html)
    @GetMapping("/students/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            StudentResponse student = studentService.getStudentById(id);

            CreateStudentRequest request = new CreateStudentRequest();
            request.setFirstName(student.getFirstName());
            request.setLastName(student.getLastName());
            request.setEmail(student.getEmail());
            request.setLevel(student.getLevel());
            request.setDepartmentId(student.getDepartmentId());

            model.addAttribute("student", request);
            model.addAttribute("studentId", id);
            return "editstudentsdet";
        } catch (StudentNotFound ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "Cannot edit: Student with ID " + id + " does not exist.");
            return "redirect:/studentMG/students/update-page";
        }
    }

    @PostMapping("/students/edit/{id}")
    public String updateStudent(@PathVariable Integer id,
                                @Valid @ModelAttribute("student") CreateStudentRequest request,
                                BindingResult result,
                                RedirectAttributes redirectAttributes,
                                Model model) {

        if (result.hasErrors()) {
            model.addAttribute("studentId", id);
            return "editstudentsdet";
        }

        try {
            studentService.updateStudent(id, request);
            redirectAttributes.addFlashAttribute("successMessage", "Student updated successfully!");
            return "redirect:/studentMG/students/edit/{id}";
        } catch (DuplicateEmail ex) {
            result.rejectValue("email", "duplicate", "Email already in use by another student");
            model.addAttribute("studentId", id);
            return "editstudentsdet";
        } catch (DataIntegrityViolationException ex) {
            result.rejectValue("departmentId", "missing", "That department does not exist");
            model.addAttribute("studentId", id);
            return "editstudentsdet";
        }
    }

    //Delete Action to delete students (removestudents.html)
    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            studentService.deleteStudent(id);
            redirectAttributes.addFlashAttribute("successMessage", "Student has been removed successfully!");
            return "redirect:/studentMG/students/delete-page";
        } catch (StudentNotFound ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "Student with ID " + id + " does not exist.");
            return "redirect:/studentMG/students/delete-page";
        }
    }
}