package com.school_management.service;

import com.school_management.entity.School;
import com.school_management.entity.Tutor;
import com.school_management.exception.SchoolNotFoundException;
import com.school_management.exception.UserNotFoundException;
import com.school_management.repository.SchoolRepository;
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
public class TutorServiceTest {
    @Mock
    private TutorRepository tutorRepository;
    @Mock
    private SchoolRepository schoolRepository;
    @InjectMocks
    private TutorService tutorService;
    private Tutor tutor;

    @BeforeAll
    public static void toStartTutorService() {
        System.out.println("Tutor Service Test case execution has been started");
    }

    @BeforeEach
    void setUp() {
        this.tutor = new Tutor();
        this.tutor.setId(1);
        this.tutor.setName("Ravi");
    }

//    @Test
//    public void testCreateTutor() {
//        when(this.tutorRepository.save(any(Tutor.class))).thenReturn(this.tutor);
//        final Tutor createdTutor = this.tutorService.createTutor(this.tutor);
//        assertNotNull(createdTutor);
//        assertEquals(1, createdTutor.getId());
//        assertEquals("Ravi", createdTutor.getName());
//        verify(this.tutorRepository, times(1)).save(any(Tutor.class));
//    }
@Test
void testCreateTutor_Success() {
    School school = new School();
    school.setId(1);
    Tutor tutor = new Tutor();
    tutor.setSchool(school);
    when(schoolRepository.findById(1)).thenReturn(Optional.of(school));
    when(tutorRepository.save(any(Tutor.class))).thenReturn(tutor);
    Tutor result = tutorService.createTutor(tutor);
    assertNotNull(result);
    assertEquals(1, result.getSchool().getId());

    verify(schoolRepository, times(1)).findById(1);
    verify(tutorRepository, times(1)).save(tutor);
}

    @Test
    void testCreateTutor_SchoolNotFound() {

        School school = new School();
        school.setId(99);
        Tutor tutor = new Tutor();
        tutor.setSchool(school);
        when(schoolRepository.findById(99)).thenReturn(Optional.empty());
        SchoolNotFoundException exception = assertThrows(SchoolNotFoundException.class, () -> {
            tutorService.createTutor(tutor);
        });
        assertEquals("School with ID 99 not found", exception.getMessage());

        verify(schoolRepository, times(1)).findById(99);
        verify(tutorRepository, never()).save(any());
    }
    @Test
    void testRetrieveById() {
        when(this.tutorRepository.findById(any(Integer.class))).thenReturn(Optional.of(this.tutor));
        final Tutor retrievedTutor = this.tutorService.findById(this.tutor.getId());
        assertNotNull(retrievedTutor);
        assertEquals(1, retrievedTutor.getId());
        assertEquals("Ravi", retrievedTutor.getName());
        verify(this.tutorRepository, times(1)).findById(this.tutor.getId());
    }

    @Test
    void testRetrieveAll() {
        when(this.tutorRepository.findAll()).thenReturn(List.of(this.tutor));
        final List<Tutor> tutor = this.tutorService.getAlltutor();
        assertNotNull(tutor);
        assertFalse(tutor.isEmpty());
        assertEquals(1, tutor.size());
        verify(this.tutorRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteById() {
        when(this.tutorRepository.existsById(1)).thenReturn(true);
        doNothing().when(this.tutorRepository).deleteById(1);

        final String result = this.tutorService.deleteById(1);

        assertEquals(Constant.REMOVE, result);
        verify(this.tutorRepository, times(1)).existsById(1);
        verify(this.tutorRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteByIdNotFound() {
        when(this.tutorRepository.existsById(any(Integer.class))).thenReturn(false);
        final UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            this.tutorService.deleteById(this.tutor.getId());
        });
        assertEquals(Constant.NOT_FOUND + " " + this.tutor.getId(), exception.getMessage());
    }

    @Test
    public void testUpdateById() {

        final Tutor existingCourse = new Tutor();
        existingCourse.setName("Ravi");

        when(this.tutorRepository.findById(any(Integer.class))).thenReturn(Optional.of(this.tutor));
        when(this.tutorRepository.save(any(Tutor.class))).thenReturn(existingCourse);

        final Tutor result = this.tutorService.updateById(existingCourse, this.tutor.getId());
        assertNotNull(result);
        assertEquals("Ravi", result.getName());

        verify(this.tutorRepository, times(1)).findById(this.tutor.getId());
        verify(this.tutorRepository, times(1)).save(any(Tutor.class));
    }

    @Test
    public void testUpdateByIdNotFound() {
        int courseId = 1;
        final Tutor updatedData = new Tutor();
        updatedData.setName("Ravi");
        when(this.tutorRepository.findById(any(Integer.class))).thenReturn(Optional.empty());

        final UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            this.tutorService.updateById(updatedData, courseId);
        });

        assertEquals(Constant.ID_DOES_NOT_EXIST, exception.getMessage());
        verify(this.tutorRepository, never()).save(any());
    }
}
