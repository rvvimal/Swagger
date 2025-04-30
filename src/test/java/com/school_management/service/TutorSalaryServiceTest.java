package com.school_management.service;

import com.school_management.dto.TutorSalaryDTO;
import com.school_management.entity.School;
import com.school_management.entity.Tutor;
import com.school_management.entity.TutorSalary;
import com.school_management.exception.SchoolNotFoundException;
import com.school_management.exception.UserNotFoundException;
import com.school_management.repository.TutorRepository;
import com.school_management.repository.TutorSalaryRepository;
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
public class TutorSalaryServiceTest {
    @Mock
    private TutorRepository tutorRepository;
    @Mock
    private TutorSalaryRepository tutorSalaryRepository;
    @InjectMocks
    private TutorSalaryService tutorSalaryService;
    private TutorSalary tutorSalary;

    @BeforeAll
    public static void toStartTutorSalaryService() {
        System.out.println("TutorSalary Service Test case execution has been started");
    }

    @BeforeEach
    void setUp() {
        final Tutor tutor = new Tutor();
        tutor.setId(1);
        this.tutorSalary = new TutorSalary();
        this.tutorSalary.setId(1);
        this.tutorSalary.setPaid("paid");
        this.tutorSalary.setMonth("June");
        this.tutorSalary.setAmount(2500.0);
        this.tutorSalary.setTutor(tutor);
    }

//    @Test
//    public void testCreateTutorSalary() {
//        when(this.tutorSalaryRepository.save(any(TutorSalary.class))).thenReturn(this.tutorSalary);
//        final TutorSalary createdTutorSalary = this.tutorSalaryService.createTutorSalary(this.tutorSalary);
//        assertNotNull(createdTutorSalary);
//        assertEquals(1, createdTutorSalary.getId());
//        assertEquals("paid", createdTutorSalary.getPaid());
//        assertEquals("June", createdTutorSalary.getMonth());
//        assertEquals(2500.0, createdTutorSalary.getAmount());
//        assertEquals(1, createdTutorSalary.getTutor().getId());
//        verify(this.tutorSalaryRepository, times(1)).save(any(TutorSalary.class));
//    }

    @Test
    void testCreateTutorSalary_Success() {
        Tutor tutor = new Tutor();
        tutor.setId(1);
        TutorSalary tutorSalary = new TutorSalary();
        tutorSalary.setTutor(tutor);
        when(tutorRepository.findById(1)).thenReturn(Optional.of(tutor));
        when(tutorSalaryRepository.save(tutorSalary)).thenReturn(tutorSalary);
        TutorSalary savedTutorSalary = tutorSalaryService.createTutorSalary(tutorSalary);
        assertNotNull(savedTutorSalary);
        assertEquals(1, savedTutorSalary.getTutor().getId());
        verify(tutorRepository, times(1)).findById(1);
        verify(tutorSalaryRepository, times(1)).save(tutorSalary);
    }

    @Test
    void testCreateTutorSalary_TutorNotFound() {
        Tutor tutor = new Tutor();
        tutor.setId(99);
        TutorSalary tutorSalary = new TutorSalary();
        tutorSalary.setTutor(tutor);
        when(tutorRepository.findById(99)).thenReturn(Optional.empty());
        assertThrows(SchoolNotFoundException.class, () -> {
            tutorSalaryService.createTutorSalary(tutorSalary);
        });

        verify(tutorRepository, times(1)).findById(99);
        verify(tutorSalaryRepository, never()).save(any(TutorSalary.class));
    }

    @Test
    void testGetAllTutorSalary_ReturnsList() {
        List<TutorSalary> mockSalaries = List.of(new TutorSalary(), new TutorSalary());
        when(tutorSalaryRepository.findAll()).thenReturn(mockSalaries);
        List<TutorSalary> result = tutorSalaryService.getAlltutorSalary();
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(tutorSalaryRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveById() {
        when(this.tutorSalaryRepository.findById(any(Integer.class))).thenReturn(Optional.of(this.tutorSalary));
        final TutorSalary retrievedTutorSalary = this.tutorSalaryService.findById(this.tutorSalary.getId());
        assertNotNull(retrievedTutorSalary);
        assertEquals(1, retrievedTutorSalary.getId());
        assertEquals("paid", retrievedTutorSalary.getPaid());
        assertEquals("June", retrievedTutorSalary.getMonth());
        assertEquals(2500.0, retrievedTutorSalary.getAmount());
        assertEquals(1, retrievedTutorSalary.getTutor().getId());
        verify(this.tutorSalaryRepository, times(1)).findById(this.tutorSalary.getId());
    }

    @Test
    void testRetrieveAll() {
        when(this.tutorSalaryRepository.findAll()).thenReturn(List.of(this.tutorSalary));
        final List<TutorSalary> tutorSalary = this.tutorSalaryService.getAlltutorSalary();
        assertNotNull(tutorSalary);
        assertFalse(tutorSalary.isEmpty());
        assertEquals(1, tutorSalary.size());
        verify(this.tutorSalaryRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteById() {
        when(this.tutorSalaryRepository.existsById(1)).thenReturn(true);
        doNothing().when(this.tutorSalaryRepository).deleteById(1);

        final String result = this.tutorSalaryService.deleteById(1);

        assertEquals(Constant.REMOVE, result);
        verify(this.tutorSalaryRepository, times(1)).existsById(1);
        verify(this.tutorSalaryRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteByIdNotFound() {
        when(this.tutorSalaryRepository.existsById(any(Integer.class))).thenReturn(false);
        final UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            this.tutorSalaryService.deleteById(this.tutorSalary.getId());
        });
        assertEquals(Constant.NOT_FOUND + " " + this.tutorSalary.getId(), exception.getMessage());
    }

    @Test
    public void testUpdateById() {

        final TutorSalary existingTutorSalary = new TutorSalary();
        existingTutorSalary.setMonth("June");

        when(this.tutorSalaryRepository.findById(any(Integer.class))).thenReturn(Optional.of(this.tutorSalary));
        when(this.tutorSalaryRepository.save(any(TutorSalary.class))).thenReturn(existingTutorSalary);

        final TutorSalary result = this.tutorSalaryService.updateById(existingTutorSalary, this.tutorSalary.getId());
        assertNotNull(result);
        assertEquals("June", result.getMonth());

        verify(this.tutorSalaryRepository, times(1)).findById(this.tutorSalary.getId());
        verify(this.tutorSalaryRepository, times(1)).save(any(TutorSalary.class));
    }

    @Test
    public void testUpdateByIdNotFound() {
        int tutorSalaryId = 1;
        final TutorSalary updatedData = new TutorSalary();
        updatedData.setMonth("June");
        when(this.tutorSalaryRepository.findById(any(Integer.class))).thenReturn(Optional.empty());

        final UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            this.tutorSalaryService.updateById(updatedData, tutorSalaryId);
        });

        assertEquals(Constant.ID_DOES_NOT_EXIST, exception.getMessage());
        verify(this.tutorSalaryRepository, never()).save(any());
    }
    @Test
    void testGetFeeAmount_ShouldReturnFilteredTutorSalaryDTOs() {
        int schoolId = 1;
        final Tutor tutor = new Tutor();
        tutor.setId(1);
        tutor.setName("Ravi");
        final School school = new School();
        school.setId(schoolId);
        tutor.setSchool(school);

        final TutorSalary tutorSalarys = new TutorSalary();
        tutorSalarys.setTutor(tutor);
        tutorSalarys.setAmount(2500.0);

        final TutorSalary tutorSalary = new TutorSalary();
        tutorSalary.setTutor(tutor);
        tutorSalary.setAmount(1800.0);

        final List<TutorSalary> tutorSalaries = List.of(tutorSalarys, tutorSalary);

        when(this.tutorSalaryRepository.findByTutor_School_Id(schoolId)).thenReturn(tutorSalaries);
        final List<TutorSalaryDTO> result = this.tutorSalaryService.getFeeAmount(schoolId);

        assertEquals(1, result.size());
        final TutorSalaryDTO dto = result.get(0);
        assertEquals(1, dto.getTutorId());
        assertEquals("Ravi", dto.getTutorName());
        assertEquals(schoolId, dto.getSchoolId());
        assertEquals(2500.0, dto.getAmount());
    }
}
