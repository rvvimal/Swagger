package com.school_management.service;

import com.school_management.entity.School;
import com.school_management.exception.UserNotFoundException;
import com.school_management.repository.SchoolRepository;
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
public class SchoolServiceTest {
    @Mock
    private SchoolRepository schoolRepository;
    @InjectMocks
    private SchoolService schoolService;
    private School school;

    @BeforeAll
    public static void toStartSchoolService() {
        System.out.println(" School Service Test case execution has been started");
    }

    @BeforeEach
    void setUp() {
        this.school = new School();
        this.school.setId(1);
        this.school.setName("SRM School");
        this.school.setAddress("Kallakurichi");
        this.school.setContact(7942699116L);
    }

    @Test
    public void testCreateSchool() {
        when(this.schoolRepository.save(any(School.class))).thenReturn(this.school);
        final School createdSchool = this.schoolService.createSchool(this.school);
        assertNotNull(createdSchool);
        assertEquals("SRM School", createdSchool.getName());
        assertEquals("Kallakurichi", createdSchool.getAddress());
        assertEquals(7942699116L, createdSchool.getContact());
        verify(this.schoolRepository, times(1)).save(any(School.class));
    }

    @Test
   public void testRetrieveById() {
        when(this.schoolRepository.findById(any(Integer.class))).thenReturn(Optional.of(this.school));
        final School retrievedSchool = this.schoolService.findById(this.school.getId());
        assertNotNull(retrievedSchool);
        assertEquals("SRM School", retrievedSchool.getName());
        assertEquals("Kallakurichi", retrievedSchool.getAddress());
        assertEquals(7942699116L, retrievedSchool.getContact());
        verify(this.schoolRepository, times(1)).findById(this.school.getId());
    }

    @Test
   public  void testRetrieveAll() {
        when(this.schoolRepository.findAll()).thenReturn(List.of(this.school));
        final List<School> schools = this.schoolService.getAllSchool();
        assertNotNull(schools);
        assertFalse(schools.isEmpty());
        assertEquals(1, schools.size());
        verify(this.schoolRepository, times(1)).findAll();
    }
    @Test
    public void testDeleteById() {
        when(this.schoolRepository.existsById(1)).thenReturn(true);
        doNothing().when(this.schoolRepository).deleteById(1);

       final String result = this.schoolService.deleteById(1);

        assertEquals(Constant.REMOVE, result);
        verify(this.schoolRepository, times(1)).existsById(1);
        verify(this.schoolRepository, times(1)).deleteById(1);
    }
    @Test
    public void testDeleteByIdNotFound() {
        when(this.schoolRepository.existsById(any(Integer.class))).thenReturn(false);
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            this.schoolService.deleteById(this.school.getId());
        });
        assertEquals(Constant.NOT_FOUND + " " + this.school.getId(), exception.getMessage());
    }

    @Test
    public void testUpdateById() {

        final School existingSchool = new School();
        existingSchool.setName("SRM School");
        existingSchool.setAddress("Kallakurichi");
        existingSchool.setContact(7942699116L);

        when(this.schoolRepository.findById(any(Integer.class))).thenReturn(Optional.of(this.school));
        when(this.schoolRepository.save(any(School.class))).thenReturn(existingSchool);

       final School result = this.schoolService.updateById(existingSchool, this.school.getId());
        assertNotNull(result);
        assertEquals("SRM School", result.getName());
        assertEquals("Kallakurichi", result.getAddress());
        assertEquals(7942699116L, result.getContact());

        verify(this.schoolRepository, times(1)).findById(this.school.getId());
        verify(this.schoolRepository, times(1)).save(any(School.class));
    }

    @Test
    public void testUpdateByIdNotFound() {
        int schoolId = 1;
        final School updatedData = new School();
        updatedData.setName("SRM School");
        updatedData.setAddress("Kallakurichi");
        updatedData.setContact(7942699116L);
        when(this.schoolRepository.findById(any(Integer.class))).thenReturn(Optional.empty());

        final UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            this.schoolService.updateById(updatedData, schoolId);
        });

        assertEquals(Constant.ID_DOES_NOT_EXIST, exception.getMessage());
        verify(this.schoolRepository, never()).save(any());
    }
}

