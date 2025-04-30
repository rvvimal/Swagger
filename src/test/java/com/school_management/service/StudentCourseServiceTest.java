package com.school_management.service;

import com.school_management.dto.SchoolDTO;
import com.school_management.dto.SchoolDetailsDTO;
import com.school_management.dto.StudentCourseDTO;
import com.school_management.entity.*;
import com.school_management.exception.UserNotFoundException;
import com.school_management.repository.StudentCourseRepository;
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

public class StudentCourseServiceTest {
    @Mock
    private StudentCourseRepository studentCourseRepository;
    @InjectMocks
    private StudentCourseService studentCourseService;
    private StudentCourse studentCourse;

    @BeforeAll
    public static void toStartStudentCourseService() {
        System.out.println(" StudentCourse Service Test case execution has been started");
    }

    @BeforeEach
    void setUp() {
        final Student student = new Student();
        student.setId(1);
        final Tutor tutor = new Tutor();
        tutor.setId(2);
        final Course course = new Course();
        course.setId(2);
        this.studentCourse = new StudentCourse();
        this.studentCourse.setId(1);
        this.studentCourse.setStudent(student);
        this.studentCourse.setCourse(course);
        this.studentCourse.setTutor(tutor);
    }

    @Test
    public void testCreateStudentCourse() {
        when(this.studentCourseRepository.save(any(StudentCourse.class))).thenReturn(this.studentCourse);
        final StudentCourse createdStudentCourse = this.studentCourseService.createStudentCourse(this.studentCourse);
        assertNotNull(createdStudentCourse);
        assertEquals(1, createdStudentCourse.getId());
        assertEquals(1, createdStudentCourse.getStudent().getId());
        assertEquals(2, createdStudentCourse.getCourse().getId());
        assertEquals(2, createdStudentCourse.getTutor().getId());

        verify(this.studentCourseRepository, times(1)).save(any(StudentCourse.class));
    }

    @Test
    void testRetrieveById() {
        when(this.studentCourseRepository.findById(any(Integer.class))).thenReturn(Optional.of(this.studentCourse));
        final StudentCourse retrievedStudentCourse = this.studentCourseService.findById(this.studentCourse.getId());
        assertNotNull(retrievedStudentCourse);
        assertEquals(1, retrievedStudentCourse.getId());
        assertEquals(1, retrievedStudentCourse.getStudent().getId());
        assertEquals(2, retrievedStudentCourse.getCourse().getId());
        assertEquals(2, retrievedStudentCourse.getTutor().getId());
        verify(this.studentCourseRepository, times(1)).findById(this.studentCourse.getId());
    }

    @Test
    void testRetrieveAll() {
        when(this.studentCourseRepository.findAll()).thenReturn(List.of(this.studentCourse));
        final List<StudentCourse> studentCourse = this.studentCourseService.getAllStudentCourse();
        assertNotNull(studentCourse);
        assertFalse(studentCourse.isEmpty());
        assertEquals(1, studentCourse.size());
        verify(this.studentCourseRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteById() {
        when(this.studentCourseRepository.existsById(1)).thenReturn(true);
        doNothing().when(this.studentCourseRepository).deleteById(1);

        final String result = this.studentCourseService.deleteById(1);

        assertEquals(Constant.REMOVE, result);
        verify(this.studentCourseRepository, times(1)).existsById(1);
        verify(this.studentCourseRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteByIdNotFound() {
        when(this.studentCourseRepository.existsById(any(Integer.class))).thenReturn(false);
       final UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            this.studentCourseService.deleteById(this.studentCourse.getId());
        });
        assertEquals(Constant.NOT_FOUND + " " + studentCourse.getId(), exception.getMessage());
    }

    @Test
    public void testRetrieveAllStudentDetail() {
       final School school = new School();
        school.setName("SRM School");
        final Student student = new Student();
        student.setFirstName("Kavinkumar");
        student.setSchool(school);
        final Course course = new Course();
        course.setName("Maths");
        final Tutor tutor = new Tutor();
        tutor.setName("Ravi");
       final StudentCourse studentCourse = new StudentCourse();
        studentCourse.setStudent(student);
        studentCourse.setCourse(course);
        studentCourse.setTutor(tutor);
        final List<StudentCourse> studentCourseList = List.of(studentCourse);
        when(this.studentCourseRepository.findAll()).thenReturn(studentCourseList);
        final List<StudentCourseDTO> result = this.studentCourseService.retrieveAllStudentDetail();
        assertEquals(1, result.size());
        StudentCourseDTO dto = result.get(0);
        assertEquals("Maths", dto.getCourseName());
        assertEquals("Kavinkumar", dto.getStudentName());
        assertEquals("Ravi", dto.getTutorName());
        assertEquals("SRM School", dto.getSchoolName());
        verify(this.studentCourseRepository, times(1)).findAll();
    }

    @Test
    public void testGetSchoolPages() {
        final School school = new School();
        school.setName("SRM School");
        final Student student = new Student();
        student.setId(1);
        student.setFirstName("Kavinkumar");
        student.setLastName("G");
        student.setEnrollmentDate("25.06.2025");
        student.setSchool(school);

        final Course course = new Course();
        course.setId(2);
        course.setName("Maths");

        final Tutor tutor = new Tutor();
        tutor.setId(2);
        tutor.setName("Ravi");

        final StudentCourse studentCourse = new StudentCourse();
        studentCourse.setId(1);
        studentCourse.setStudent(student);
        studentCourse.setCourse(course);
        studentCourse.setTutor(tutor);

        final List<StudentCourse> studentCourseList = List.of(studentCourse);
        final Page<StudentCourse> mockPage = new PageImpl<>(studentCourseList);
        when(this.studentCourseRepository.findByStudent_School_Id(eq(1), any(Pageable.class)))
                .thenReturn(mockPage);
       final Page<SchoolDetailsDTO> result = this.studentCourseService.getSchoolPages(1, 0, 10);
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());

        final SchoolDetailsDTO dto = result.getContent().get(0);
        assertEquals(1, dto.getId());
        assertEquals("SRM School", dto.getSchoolName());
        assertEquals(2, dto.getTutorId());
        assertEquals("Ravi", dto.getTutorName());
        assertEquals(2, dto.getCourseId());
        assertEquals("Maths", dto.getCourseName());
        assertEquals(1, dto.getStudentId());
        assertEquals("Kavinkumar", dto.getStudentFirstName());
        assertEquals("G", dto.getStudentLastName());
        assertEquals("25.06.2025", dto.getEnrollmentDate());
        verify(this.studentCourseRepository, times(1)).findByStudent_School_Id(eq(1), any(Pageable.class));
    }

    @Test
    public void testGetCourseCount() {
        final School school = new School();
        school.setId(1);
        school.setName("SRM School");
        Student student = new Student();
        student.setSchool(school);
        final StudentCourse studentCourse = new StudentCourse();
        studentCourse.setStudent(student);
        final List<StudentCourse> studentCourses = List.of(studentCourse);
        when(this.studentCourseRepository.findByStudent_School_Id(1)).thenReturn(studentCourses);
        final SchoolDTO result = this.studentCourseService.getCourseCount(1);
        assertNotNull(result);
        assertEquals(1, result.getSchoolId());
        assertEquals("SRM School", result.getSchoolName());
        assertEquals(1, result.getTotalCourse());
        verify(this.studentCourseRepository, times(1)).findByStudent_School_Id(1);
    }

}
