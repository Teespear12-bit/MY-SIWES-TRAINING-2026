package com.student.studentapi.service;

import com.student.studentapi.dto.CreateStudentRequest;
import com.student.studentapi.dto.StudentResponse;
import com.student.studentapi.execption.DuplicateEmail;
import com.student.studentapi.execption.StudentNotFound;
import com.student.studentapi.model.Student;
import com.student.studentapi.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public StudentResponse createStudent(CreateStudentRequest request) {
        log.info("Attempting to create student with email: {}", request.getEmail());

        if (studentRepository.existsByEmail(request.getEmail())) {
            log.warn("Failed to create student. Email already exists: {}", request.getEmail());
            throw new DuplicateEmail(request.getEmail());
        }

        Student student = new Student();
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setLevel(request.getLevel());
        student.setDepartmentId(request.getDepartmentId());

        Student savedStudent = studentRepository.save(student);
        log.info("Successfully created student with ID: {}", savedStudent.getId());

        return mapToResponse(savedStudent);
    }

    @Transactional(readOnly = true)
    public List<StudentResponse> getAllStudents() {
        log.info("Fetching all students from database");
        return studentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public StudentResponse getStudentById(Integer id) {
        log.info("Fetching student with ID: {}", id);
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Student not found with ID: {}", id);
                    return new StudentNotFound(id);
                });
        return mapToResponse(student);
    }

    @Transactional
    public StudentResponse updateStudent(Integer id, CreateStudentRequest request) {
        log.info("Updating student with ID: {}", id);
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFound(id));

        if (studentRepository.existsByEmailAndIdNot(request.getEmail(), id)) {
            log.warn("Cannot update student {}. Email {} belongs to another account", id, request.getEmail());
            throw new DuplicateEmail(request.getEmail());
        }

        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setLevel(request.getLevel());
        student.setDepartmentId(request.getDepartmentId());

        Student updatedStudent = studentRepository.save(student);
        log.info("Successfully updated student with ID: {}", updatedStudent.getId());

        return mapToResponse(updatedStudent);
    }

    @Transactional
    public void deleteStudent(Integer id) {
        log.info("Deleting student with ID: {}", id);
        if (!studentRepository.existsById(id)) {
            log.error("Failed to delete. Student not found with ID: {}", id);
            throw new StudentNotFound(id);
        }
        studentRepository.deleteById(id);
        log.info("Successfully deleted student with ID: {}", id);
    }

    private StudentResponse mapToResponse(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getLevel(),
                student.getDepartmentId()
        );
    }
}