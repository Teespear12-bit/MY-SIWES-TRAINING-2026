package com.student.studentapi.service;

import com.student.studentapi.dto.CreateStudentRequest;
import com.student.studentapi.dto.StudentResponse;
import com.student.studentapi.execption.DuplicateEmail;
import com.student.studentapi.execption.StudentNotFound;
import com.student.studentapi.model.Student;
import com.student.studentapi.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private Student sampleStudent;
    private CreateStudentRequest sampleRequest;

    @BeforeEach
    void setUp() {
        sampleStudent = new Student();
        sampleStudent.setId(1);
        sampleStudent.setFirstName("John");
        sampleStudent.setLastName("Doe");
        sampleStudent.setEmail("john.doe@example.com");
        sampleStudent.setLevel("300 Level");
        sampleStudent.setDepartmentId(10);

        sampleRequest = new CreateStudentRequest();
        sampleRequest.setFirstName("John");
        sampleRequest.setLastName("Doe");
        sampleRequest.setEmail("john.doe@example.com");
        sampleRequest.setLevel("300 Level");
        sampleRequest.setDepartmentId(10);
    }

    // ==========================================
    // 5 SUCCESSFUL TEST CASES
    // ==========================================

    @Test
    @DisplayName("1. createStudent - Success")
    void createStudent_Success() {
        when(studentRepository.existsByEmail(sampleRequest.getEmail())).thenReturn(false);
        when(studentRepository.save(any(Student.class))).thenReturn(sampleStudent);

        StudentResponse response = studentService.createStudent(sampleRequest);

        assertNotNull(response);
        assertEquals(1, response.getId());
        assertEquals("john.doe@example.com", response.getEmail());
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    @DisplayName("2. getStudentById - Success")
    void getStudentById_Success() {
        when(studentRepository.findById(1)).thenReturn(Optional.of(sampleStudent));

        StudentResponse response = studentService.getStudentById(1);

        assertNotNull(response);
        assertEquals(1, response.getId());
        assertEquals("John", response.getFirstName());
    }

    @Test
    @DisplayName("3. getAllStudents - Success")
    void getAllStudents_Success() {
        when(studentRepository.findAll()).thenReturn(List.of(sampleStudent));

        List<StudentResponse> responses = studentService.getAllStudents();

        assertEquals(1, responses.size());
        assertEquals("John", responses.get(0).getFirstName());
    }

    @Test
    @DisplayName("4. updateStudent - Success")
    void updateStudent_Success() {
        when(studentRepository.findById(1)).thenReturn(Optional.of(sampleStudent));
        when(studentRepository.existsByEmailAndIdNot(sampleRequest.getEmail(), 1)).thenReturn(false);
        when(studentRepository.save(any(Student.class))).thenReturn(sampleStudent);

        StudentResponse response = studentService.updateStudent(1, sampleRequest);

        assertNotNull(response);
        assertEquals("john.doe@example.com", response.getEmail());
    }

    @Test
    @DisplayName("5. deleteStudent - Success")
    void deleteStudent_Success() {
        when(studentRepository.existsById(1)).thenReturn(true);
        doNothing().when(studentRepository).deleteById(1);

        assertDoesNotThrow(() -> studentService.deleteStudent(1));
        verify(studentRepository, times(1)).deleteById(1);
    }

    // ==========================================
    // 5 FAILURE / EXCEPTION TEST CASES
    // ==========================================

    @Test
    @DisplayName("6. createStudent - Fails when email already exists")
    void createStudent_DuplicateEmail_ThrowsException() {
        when(studentRepository.existsByEmail(sampleRequest.getEmail())).thenReturn(true);

        assertThrows(DuplicateEmail.class, () -> studentService.createStudent(sampleRequest));
        verify(studentRepository, never()).save(any(Student.class));
    }

    @Test
    @DisplayName("7. getStudentById - Fails when student ID does not exist")
    void getStudentById_NotFound_ThrowsException() {
        when(studentRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(StudentNotFound.class, () -> studentService.getStudentById(99));
    }

    @Test
    @DisplayName("8. updateStudent - Fails when student ID does not exist")
    void updateStudent_NotFound_ThrowsException() {
        when(studentRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(StudentNotFound.class, () -> studentService.updateStudent(99, sampleRequest));
    }

    @Test
    @DisplayName("9. updateStudent - Fails when updated email is used by another student")
    void updateStudent_DuplicateEmail_ThrowsException() {
        when(studentRepository.findById(1)).thenReturn(Optional.of(sampleStudent));
        when(studentRepository.existsByEmailAndIdNot(sampleRequest.getEmail(), 1)).thenReturn(true);

        assertThrows(DuplicateEmail.class, () -> studentService.updateStudent(1, sampleRequest));
        verify(studentRepository, never()).save(any(Student.class));
    }

    @Test
    @DisplayName("10. deleteStudent - Fails when student ID does not exist")
    void deleteStudent_NotFound_ThrowsException() {
        when(studentRepository.existsById(99)).thenReturn(false);

        assertThrows(StudentNotFound.class, () -> studentService.deleteStudent(99));
        verify(studentRepository, never()).deleteById(anyInt());
    }
}