package com.school_management.service;

import com.school_management.dto.PaginationResponse;
import com.school_management.dto.StudentDetailsDTO;
import com.school_management.entity.School;
import com.school_management.entity.Student;
import com.school_management.exception.SchoolNotFoundException;
import com.school_management.exception.UserNotFoundException;
import com.school_management.repository.SchoolRepository;
import com.school_management.repository.StudentRepository;
import com.school_management.util.Constant;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock
    private SchoolRepository schoolRepository;
    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private StudentService studentService;
    private Student student;

    @BeforeAll
    public static void toStartStudentService() {
        System.out.println(" Student Service Test case execution has been started");
    }

    @BeforeEach
    void setUp() {
       this.student = new Student();
        this.student.setId(1);
        this.student.setFirstName("Kavinkumar");
        this. student.setLastName("G");
        this. student.setEmail("kavikumar@gmail.com");
        this. student.setContactNumber(9543631987L);
        this. student.setEnrollmentDate("25.06.2025");
    }

//    @Test
//    public void testCreateStudent() {
//        when(this.studentRepository.save(any(Student.class))).thenReturn(this. student);
//        final Student createdStudent = this.studentService.createStudent(this. student);
//        assertNotNull(createdStudent);
//        assertEquals("Kavinkumar", createdStudent.getFirstName());
//        assertEquals("G", createdStudent.getLastName());
//        assertEquals(9543631987L, createdStudent.getContactNumber());
//        assertEquals("kavikumar@gmail.com", createdStudent.getEmail());
//        assertEquals("25.06.2025", createdStudent.getEnrollmentDate());
//        verify(this.studentRepository, times(1)).save(any(Student.class));
//    }

    @Test
    void testCreateStudent_Success() {
        School school = new School();
        school.setId(1);
        Student student = new Student();
        student.setSchool(school);

        when(schoolRepository.findById(1)).thenReturn(Optional.of(school));
        when(studentRepository.save(student)).thenReturn(student);
        Student savedStudent = studentService.createStudent(student);
        assertNotNull(savedStudent);
        assertEquals(1, savedStudent.getSchool().getId());
        verify(schoolRepository, times(1)).findById(1);
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testCreateStudent_SchoolNotFound() {
        School school = new School();
        school.setId(2);
        Student student = new Student();
        student.setSchool(school);
        when(schoolRepository.findById(2)).thenReturn(Optional.empty());
        assertThrows(SchoolNotFoundException.class, () -> {
            studentService.createStudent(student);
        });

        verify(schoolRepository, times(1)).findById(2);
        verify(studentRepository, never()).save(any(Student.class));
    }

    @Test
    void testRetrieveById() {
        when(this.studentRepository.findById(any(Integer.class))).thenReturn(Optional.of(this.student));
        final Student retrievedStudent = this.studentService.findById(this.student.getId());
        assertNotNull(retrievedStudent);
        assertEquals("Kavinkumar", retrievedStudent.getFirstName());
        assertEquals("G", retrievedStudent.getLastName());
        assertEquals(9543631987L, retrievedStudent.getContactNumber());
        assertEquals("kavikumar@gmail.com", retrievedStudent.getEmail());
        assertEquals("25.06.2025", retrievedStudent.getEnrollmentDate());
        verify(this.studentRepository, times(1)).findById(this.student.getId());
    }

    @Test
    void testRetrieveAll() {
        when(this.studentRepository.findAll()).thenReturn(List.of(this.student));
        final List<Student> students = this.studentService.getAllStudent();
        assertNotNull(students);
        assertFalse(students.isEmpty());
        assertEquals(1, students.size());
        verify(this.studentRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteById() {
        when(this.studentRepository.existsById(1)).thenReturn(true);
        doNothing().when(this.studentRepository).deleteById(1);

        final String result = this.studentService.deleteById(1);

        assertEquals(Constant.REMOVE, result);
        verify(this.studentRepository, times(1)).existsById(1);
        verify(this.studentRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteByIdNotFound() {
        when(this.studentRepository.existsById(any(Integer.class))).thenReturn(false);
        final UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            studentService.deleteById(this.student.getId());
        });
        assertEquals(Constant.NOT_FOUND + " " + this.student.getId(), exception.getMessage());
    }

    @Test
    public void testUpdateById() {

        final Student existingStudent = new Student();
        existingStudent.setFirstName("Kavinkumar");
        existingStudent.setEmail("kavikumar@gmail.com");
        existingStudent.setContactNumber(9543631987L);

        when(this.studentRepository.findById(any(Integer.class))).thenReturn(Optional.of(this.student));
        when(this.studentRepository.save(any(Student.class))).thenReturn(existingStudent);

       final Student result = this.studentService.updateById(existingStudent, this.student.getId());
        assertNotNull(result);
        assertEquals("Kavinkumar", result.getFirstName());
        assertEquals("kavikumar@gmail.com", result.getEmail());
        assertEquals(9543631987L, result.getContactNumber());

        verify(this.studentRepository, times(1)).findById(this.student.getId());
        verify(this.studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    public void testUpdateByIdNotFound() {
        int studentId = 1;
        final Student updatedData = new Student();
        updatedData.setFirstName("Kavinkumar");
        updatedData.setEmail("kavikumar@gmail.com");
        updatedData.setContactNumber(9543631987L);
        when(this.studentRepository.findById(any(Integer.class))).thenReturn(Optional.empty());

        final UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            this.studentService.updateById(updatedData, studentId);
        });

        assertEquals(Constant.ID_DOES_NOT_EXIST, exception.getMessage());
        verify(this.studentRepository, never()).save(any());
    }

    @Test
    public void testGetSortedStudentPage() {
        final Student student = new Student();
        student.setId(1);
        student.setFirstName("Kavinkumar");

        final List<Student> studentList = List.of(student);
       final Page<Student> page = new PageImpl<>(studentList);
        when(this.studentRepository.findAll(any(Pageable.class))).thenReturn(page);
       final PaginationResponse response = this.studentService.getSortedStudentPage(0, 10, "firstName", true);
        assertNotNull(response);
        assertEquals(1, response.getTotalPages());
        assertEquals(1, response.getTotalElements());
        assertEquals(1, response.getPageSize());
        List<Student> students = (List<Student>) response.getDetails();
        assertEquals("Kavinkumar", students.get(0).getFirstName());


        verify(this.studentRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    public void testGetSortedStudentPage_InvalidPageRequest_ThrowsException() {
       final Exception exception = assertThrows(RuntimeException.class, () -> {
            this.studentService.getSortedStudentPage(-1, 0, "firstName", true);
        });
        assertEquals(Constant.NOT_FOUND, exception.getMessage());
    }
    @Test
    public void testGetStudentWithCourses() {
       final StudentDetailsDTO dto = new StudentDetailsDTO();
        dto.setStudentId(1);
        dto.setStudentName("Kavinkumar");
        dto.setCourseName("Maths");
        final List<StudentDetailsDTO> mockList = List.of(dto);
        when(this.studentRepository.findStudentWithCourses(1)).thenReturn(mockList);
       final List<StudentDetailsDTO> result = this.studentService.getStudentWithCourses(1);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Kavinkumar", result.get(0).getStudentName());
        assertEquals("Maths", result.get(0).getCourseName());
        verify(this.studentRepository, times(1)).findStudentWithCourses(1);
    }

}
