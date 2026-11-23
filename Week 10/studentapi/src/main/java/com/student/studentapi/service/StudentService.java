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

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentResponse createStudent(CreateStudentRequest request) {
        log.info("Creating student with email {}", request.getEmail());

        if (studentRepository.existsByEmail(request.getEmail())) {
            log.warn("Cannot create student, email {} already exists", request.getEmail());
            throw new DuplicateEmail(request.getEmail());
        }

        Student student = new Student();
        copyRequestToStudent(request, student);
        Student saved = studentRepository.save(student);

        log.info("Created student with id {}", saved.getId());
        return toResponse(saved);
    }

    public List<StudentResponse> getAllStudents() {
        log.info("Fetching all students");
        return studentRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public List<StudentResponse> getRecentStudents() {
        List<StudentResponse> all = getAllStudents();
        List<StudentResponse> recent = new ArrayList<>();
        for (int i = all.size() - 1; i >= 0 && recent.size() < 5; i--) {
            recent.add(all.get(i));
        }
        return recent;
    }

    public int countByLevel(String level) {
        int count = 0;
        for (Student s : studentRepository.findAll()) {
            if (level.equalsIgnoreCase(s.getLevel())) {
                count++;
            }
        }
        return count;
    }

    public StudentResponse getStudentById(Integer id) {
        log.info("Fetching student with id {}", id);
        return toResponse(findStudentOrThrow(id));
    }

    public List<StudentResponse> searchStudents(String keyword) {
        log.info("Searching students with keyword {}", keyword);
        String clean = keyword.trim();
        List<StudentResponse> matches = new ArrayList<>();

        if (clean.matches("\\d+")) {
            Integer number = Integer.parseInt(clean);

            studentRepository.findById(number).ifPresent(s -> matches.add(toResponse(s)));

            for (Student s : studentRepository.findByDepartmentId(number)) {
                if (!s.getId().equals(number)) {
                    matches.add(toResponse(s));
                }
            }
        } else {
            for (Student s : studentRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(clean, clean)) {
                matches.add(toResponse(s));
            }
        }

        return matches;
    }

    public StudentResponse updateStudent(Integer id, CreateStudentRequest request) {
        log.info("Updating student with id {}", id);
        Student student = findStudentOrThrow(id);

        if (studentRepository.existsByEmailAndIdNot(request.getEmail(), id)) {
            log.warn("Cannot update student {}, email {} belongs to someone else", id, request.getEmail());
            throw new DuplicateEmail(request.getEmail());
        }

        copyRequestToStudent(request, student);
        Student saved = studentRepository.save(student);

        log.info("Updated student with id {}", id);
        return toResponse(saved);
    }

    public void deleteStudent(Integer id) {
        log.info("Deleting student with id {}", id);

        if (!studentRepository.existsById(id)) {
            log.warn("Cannot delete, student with id {} not found", id);
            throw new StudentNotFound(id);
        }

        studentRepository.deleteById(id);
        log.info("Deleted student with id {}", id);
    }

    private Student findStudentOrThrow(Integer id) {
        return studentRepository.findById(id).orElseThrow(() -> {
            log.warn("Student with id {} not found", id);
            return new StudentNotFound(id);
        });
    }

    private void copyRequestToStudent(CreateStudentRequest request, Student student) {
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setLevel(request.getLevel());
        student.setDepartmentId(request.getDepartmentId());
    }

    private StudentResponse toResponse(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getLevel(),
                student.getDepartmentId());
    }
}