package com.school_management.service;

import com.school_management.entity.Course;
import com.school_management.exception.UserNotFoundException;
import com.school_management.repository.CourseRepository;
import com.school_management.util.Constant;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
public class CourseServiceTest {
    @Mock
    private CourseRepository courseRepository;
    @InjectMocks
    private CourseService courseService;
    private Course course;

    @BeforeAll
    public static void toStartCourseService() {
        System.out.println("Course Service Test case execution has been started");
    }

    @BeforeEach
    void setUp() {
        this.course = new Course();
        this.course.setId(1);
        this.course.setName("Maths");
    }

    @Test
    public void testCreateCourse() {
        when(this.courseRepository.save(any(Course.class))).thenReturn(this.course);
        final Course createdCourse = this.courseService.createCourse(this.course);
        assertNotNull(createdCourse);
        assertEquals(1, createdCourse.getId());
        assertEquals("Maths", createdCourse.getName());
        verify(this.courseRepository, times(1)).save(any(Course.class));
    }

    @Test
    void testRetrieveById() {
        when(this.courseRepository.findById(any(Integer.class))).thenReturn(Optional.of(this.course));
        final Course retrievedCourse = this.courseService.findById(this.course.getId());
        assertNotNull(retrievedCourse);
        assertEquals(1, retrievedCourse.getId());
        assertEquals("Maths", retrievedCourse.getName());
        verify(this.courseRepository, times(1)).findById(this.course.getId());
    }

    @Test
    void testRetrieveAll() {
        when(this.courseRepository.findAll()).thenReturn(List.of(this.course));
        List<Course> course = this.courseService.getAllCourse();
        assertNotNull(course);
        assertFalse(course.isEmpty());
        assertEquals(1, course.size());
        verify(this.courseRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteById() {
        when(this.courseRepository.existsById(1)).thenReturn(true);
        doNothing().when(this.courseRepository).deleteById(1);

        final String result = this.courseService.deleteById(1);

        assertEquals(Constant.REMOVE, result);
        verify(this.courseRepository, times(1)).existsById(1);
        verify(this.courseRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteByIdNotFound() {
        when(this.courseRepository.existsById(any(Integer.class))).thenReturn(false);
       final UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            this.courseService.deleteById(this.course.getId());
        });
        assertEquals(Constant.NOT_FOUND + " " + this.course.getId(), exception.getMessage());
    }

    @Test
    public void testUpdateById() {

        final Course existingCourse = new Course();
        existingCourse.setName("Maths");

        when(this.courseRepository.findById(any(Integer.class))).thenReturn(Optional.of(this.course));
        when(this.courseRepository.save(any(Course.class))).thenReturn(existingCourse);

        final Course result = this.courseService.updateById(existingCourse, this.course.getId());
        assertNotNull(result);
        assertEquals("Maths", result.getName());

        verify(this.courseRepository, times(1)).findById(this.course.getId());
        verify(this.courseRepository, times(1)).save(any(Course.class));
    }

    @Test
    public void testUpdateByIdNotFound() {
        int courseId = 1;
        final Course updatedData = new Course();
        updatedData.setName("Maths");
        when(this.courseRepository.findById(any(Integer.class))).thenReturn(Optional.empty());

        final UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            this.courseService.updateById(updatedData, courseId);
        });

        assertEquals(Constant.ID_DOES_NOT_EXIST, exception.getMessage());
        verify(this.courseRepository, never()).save(any());
    }
}
