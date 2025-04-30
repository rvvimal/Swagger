package com.school_management.service;

import com.school_management.entity.Course;
import com.school_management.entity.Tutor;
import com.school_management.entity.TutorCourse;
import com.school_management.exception.SchoolNotFoundException;
import com.school_management.exception.UserNotFoundException;
import com.school_management.repository.TutorCourseRepository;
import com.school_management.repository.TutorRepository;
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
public class TutorCourseServiceTest {
    @Mock
    private TutorRepository tutorRepository;
    @Mock
    private TutorCourseRepository tutorCourseRepository;
    @InjectMocks
    private TutorCourseService tutorCourseService;
    private TutorCourse tutorCourse;

    @BeforeAll
    public static void toStartTutorCourseService() {
        System.out.println("TutorCourse Service Test case execution has been started");
    }

    @BeforeEach
    void setUp() {
        final Tutor tutor = new Tutor();
        tutor.setId(2);
        final Course course = new Course();
        course.setId(2);
        this.tutorCourse = new TutorCourse();
        this.tutorCourse.setId(1);
        this.tutorCourse.setCourse(course);
        this.tutorCourse.setTutor(tutor);
    }

//    @Test
//    public void testCreateTutorCourse() {
//        when(this.tutorCourseRepository.save(any(TutorCourse.class))).thenReturn(this.tutorCourse);
//        final TutorCourse createdTutorCourse = this.tutorCourseService.createTutorCourse(this.tutorCourse);
//        assertNotNull(createdTutorCourse);
//        assertEquals(1, createdTutorCourse.getId());
//        assertEquals(2, createdTutorCourse.getCourse().getId());
//        assertEquals(2, createdTutorCourse.getTutor().getId());
//        verify(this.tutorCourseRepository, times(1)).save(any(TutorCourse.class));
//    }
@Test
void testCreateTutorCourse_Success() {
    Tutor tutor = new Tutor();
    tutor.setId(1);
    TutorCourse tutorCourse = new TutorCourse();
    tutorCourse.setTutor(tutor);
    when(tutorRepository.findById(1)).thenReturn(Optional.of(tutor));
    when(tutorCourseRepository.save(tutorCourse)).thenReturn(tutorCourse);
    TutorCourse savedTutorCourse = tutorCourseService.createTutorCourse(tutorCourse);
    assertNotNull(savedTutorCourse);
    assertEquals(1, savedTutorCourse.getTutor().getId());
    verify(tutorRepository, times(1)).findById(1);
    verify(tutorCourseRepository, times(1)).save(tutorCourse);
}

    @Test
    void testCreateTutorCourse_TutorNotFound() {
        Tutor tutor = new Tutor();
        tutor.setId(99);
        TutorCourse tutorCourse = new TutorCourse();
        tutorCourse.setTutor(tutor);
        when(tutorRepository.findById(99)).thenReturn(Optional.empty());
        assertThrows(SchoolNotFoundException.class, () -> {
            tutorCourseService.createTutorCourse(tutorCourse);
        });

        verify(tutorRepository, times(1)).findById(99);
        verify(tutorCourseRepository, never()).save(any(TutorCourse.class));
    }

    @Test
    void testRetrieveById() {
        when(this.tutorCourseRepository.findById(any(Integer.class))).thenReturn(Optional.of(this.tutorCourse));
        final TutorCourse retrievedTutorCourse = this.tutorCourseService.findById(this.tutorCourse.getId());
        assertNotNull(retrievedTutorCourse);
        assertEquals(1, retrievedTutorCourse.getId());
        assertEquals(2, retrievedTutorCourse.getCourse().getId());
        assertEquals(2, retrievedTutorCourse.getTutor().getId());
        verify(this.tutorCourseRepository, times(1)).findById(this.tutorCourse.getId());
    }

    @Test
    void testRetrieveAll() {
        when(this.tutorCourseRepository.findAll()).thenReturn(List.of(this.tutorCourse));
        final List<TutorCourse> tutorCourse = this.tutorCourseService.getAlltutorCourse();
        assertNotNull(tutorCourse);
        assertFalse(tutorCourse.isEmpty());
        assertEquals(1, tutorCourse.size());
        verify(this.tutorCourseRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteById() {
        when(this.tutorCourseRepository.existsById(1)).thenReturn(true);
        doNothing().when(this.tutorCourseRepository).deleteById(1);

        final String result = this.tutorCourseService.deleteById(1);

        assertEquals(Constant.REMOVE, result);
        verify(this.tutorCourseRepository, times(1)).existsById(1);
        verify(this.tutorCourseRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteByIdNotFound() {
        when(this.tutorCourseRepository.existsById(any(Integer.class))).thenReturn(false);
        final UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            this.tutorCourseService.deleteById(this.tutorCourse.getId());
        });
        assertEquals(Constant.NOT_FOUND + " " + this.tutorCourse.getId(), exception.getMessage());
    }

}
