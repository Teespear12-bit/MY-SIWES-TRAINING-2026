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

import java.util.List;

@Controller
@RequestMapping("/AETSS")
public class StudentViewController {

    private final StudentService studentService;

    public StudentViewController(StudentService studentService) {
        this.studentService = studentService;
    }

    // 1. Home Page -> home.html
    @GetMapping
    public String showHomePage() {
        return "home";
    }

    // 2. Dashboard Page -> Dashboard.html
    @GetMapping("/dashboard")
    public String dashBoard(Model model) {
        model.addAttribute("totalStudents", studentService.getAllStudents().size());
        model.addAttribute("count100", studentService.countByLevel("100LVL"));
        model.addAttribute("count200", studentService.countByLevel("200LVL"));
        model.addAttribute("count300", studentService.countByLevel("300LVL"));
        model.addAttribute("count400", studentService.countByLevel("400LVL"));
        model.addAttribute("count500", studentService.countByLevel("500LVL"));
        model.addAttribute("students", studentService.getRecentStudents());
        return "Dashboard";
    }

    // 3. Student Operations Menu -> operations.html
    @GetMapping("/studentMG")
    public String showMenu() {
        return "operations";
    }

    // 4. View All Students -> viewstudents.html
    @GetMapping("/studentMG/students")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "viewstudents";
    }

    // 5. Search Lookup Page -> searchstudent.html
    @GetMapping("/studentMG/students/search-page")
    public String showSearchPage() {
        return "searchstudent";
    }

    // Handles Search Form submission (by ID, First Name, Last Name, or Department ID)
    @GetMapping("/studentMG/students/search")
    public String searchStudent(@RequestParam(value = "query", required = false) String query,
                                @RequestParam(value = "id", required = false) String idFallback,
                                Model model,
                                RedirectAttributes redirectAttributes) {

        String searchText = (query != null && !query.trim().isEmpty()) ? query : idFallback;

        if (searchText == null || searchText.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please enter a Student ID, Name, or Department ID.");
            return "redirect:/AETSS/studentMG/students/search-page";
        }

        List<StudentResponse> results = studentService.searchStudents(searchText);

        if (results.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "No student found matching: " + searchText);
            return "redirect:/AETSS/studentMG/students/search-page";
        }

        if (results.size() == 1) {
            return "redirect:/AETSS/studentMG/students/" + results.get(0).getId();
        }

        model.addAttribute("results", results);
        return "searchstudent";
    }

    // 6. Student Details Page -> studentdetails.html
    @GetMapping("/studentMG/students/{id}")
    public String viewStudentDetails(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            StudentResponse student = studentService.getStudentById(id);
            model.addAttribute("student", student);
            return "studentdetails";
        } catch (StudentNotFound ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "No student found with ID: " + id);
            return "redirect:/AETSS/studentMG/students/search-page";
        }
    }

    // 7. Update Lookup Page -> updatestudent.html
    @GetMapping("/studentMG/students/update-page")
    public String showUpdateLookupPage() {
        return "updatestudent";
    }

    @GetMapping("/studentMG/students/update-search")
    public String updateStudentRedirect(@RequestParam(value = "id", required = false) Integer id,
                                        RedirectAttributes redirectAttributes) {
        if (id == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please enter a valid Student ID.");
            return "redirect:/AETSS/studentMG/students/update-page";
        }
        return "redirect:/AETSS/studentMG/students/edit/" + id;
    }

    // 8. Delete Lookup Page -> removestudent.html
    @GetMapping("/studentMG/students/delete-page")
    public String showDeleteLookupPage() {
        return "removestudent";
    }

    @GetMapping("/studentMG/students/delete-search")
    public String deleteStudentRedirect(@RequestParam(value = "id", required = false) Integer id,
                                        RedirectAttributes redirectAttributes) {
        if (id == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please enter a valid Student ID.");
            return "redirect:/AETSS/studentMG/students/delete-page";
        }
        return "redirect:/AETSS/studentMG/students/delete/" + id;
    }

    // 9. Add Student Form -> addstudents.html
    @GetMapping("/studentMG/students/new")
    public String showCreateForm(Model model) {
        if (!model.containsAttribute("student")) {
            model.addAttribute("student", new CreateStudentRequest());
        }
        return "addstudents";
    }

    @PostMapping("/studentMG/students/new")
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
            return "redirect:/AETSS/studentMG/students/new";
        } catch (DuplicateEmail ex) {
            result.rejectValue("email", "duplicate", "A student with this email already exists");
            return "addstudents";
        } catch (DataIntegrityViolationException ex) {
            result.rejectValue("departmentId", "missing", "That department does not exist");
            return "addstudents";
        }
    }

    // 10. Edit Student Form -> editstudentsdet.html
    @GetMapping("/studentMG/students/edit/{id}")
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
            return "redirect:/AETSS/studentMG/students/update-page";
        }
    }

    @PostMapping("/studentMG/students/edit/{id}")
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
            return "redirect:/AETSS/studentMG/students/edit/" + id;
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

    // 11. Delete Action (works from both removestudent.html and viewstudents.html)
    @GetMapping("/studentMG/students/delete/{id}")
    public String deleteStudent(@PathVariable Integer id,
                                @RequestParam(value = "from", required = false) String from,
                                RedirectAttributes redirectAttributes) {
        try {
            studentService.deleteStudent(id);
            redirectAttributes.addFlashAttribute("successMessage", "Student has been removed successfully!");
        } catch (StudentNotFound ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "Student with ID " + id + " does not exist.");
        }

        if ("list".equals(from)) {
            return "redirect:/AETSS/studentMG/students";
        }
        return "redirect:/AETSS/studentMG/students/delete-page";
    }
}