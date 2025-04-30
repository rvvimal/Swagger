package com.school_management.service;

import com.school_management.dto.SchoolFeeDetailsDTO;
import com.school_management.entity.FeePayment;
import com.school_management.entity.School;
import com.school_management.entity.Student;
import com.school_management.exception.SchoolNotFoundException;
import com.school_management.exception.UserNotFoundException;
import com.school_management.repository.FeePaymentRepository;
import com.school_management.repository.StudentRepository;
import com.school_management.util.Constant;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FeePaymentServiceTest {
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private FeePaymentRepository feePaymentRepository;
    @InjectMocks
    private FeePaymentService feePaymentService;
    private FeePayment feePayment;

    @BeforeAll
    public static void toStartFeePaymentService() {
        System.out.println(" FeePayment Service Test case execution has been started");
    }

    @BeforeEach
    void setUp() {
        this.feePayment = new FeePayment();
        final Student student = new Student();
        student.setId(1);
        this.feePayment.setId(1);
        this.feePayment.setFee_Term("first Term");
        this.feePayment.setStatus("paid");
        this.feePayment.setAmount(2500.0);
        final Timestamp timestamp = Timestamp.valueOf(LocalDateTime.of(2021, 8, 9, 5, 30));
        this.feePayment.setDate(timestamp);
        this.feePayment.setStudent(student);
    }

//    @Test
//    public void testCreateFeePayment() {
//        when(this.feePaymentRepository.save(any(FeePayment.class))).thenReturn(this.feePayment);
//        final FeePayment createdFeePayment = this.feePaymentService.createFeePayment(this.feePayment);
//        assertNotNull(createdFeePayment);
//        assertEquals("first Term", createdFeePayment.getFee_Term());
//        assertEquals("paid", createdFeePayment.getStatus());
//        assertEquals(2500.0, createdFeePayment.getAmount());
//        assertEquals(Timestamp.valueOf("2021-08-09 05:30:00"), createdFeePayment.getDate());
//        assertEquals(1, createdFeePayment.getStudent().getId());
//        verify(this.feePaymentRepository, times(1)).save(any(FeePayment.class));
//    }

    @Test
    void testCreateFeePayment_Success() {
        Student student = new Student();
        student.setId(1);

        FeePayment feePayment = new FeePayment();
        feePayment.setStudent(student);

        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        when(feePaymentRepository.save(feePayment)).thenReturn(feePayment);
        FeePayment savedPayment = feePaymentService.createFeePayment(feePayment);
        assertNotNull(savedPayment);
        assertEquals(1, savedPayment.getStudent().getId());
        verify(studentRepository, times(1)).findById(1);
        verify(feePaymentRepository, times(1)).save(feePayment);
    }

    @Test
    void testCreateFeePayment_StudentNotFound() {
        Student student = new Student();
        student.setId(2);
        FeePayment feePayment = new FeePayment();
        feePayment.setStudent(student);
        when(studentRepository.findById(2)).thenReturn(Optional.empty());
        assertThrows(SchoolNotFoundException.class, () -> {
            feePaymentService.createFeePayment(feePayment);
        });

        verify(studentRepository, times(1)).findById(2);
        verify(feePaymentRepository, never()).save(any(FeePayment.class));
    }

    @Test
    void testRetrieveById() {
        when(this.feePaymentRepository.findById(any(Integer.class))).thenReturn(Optional.of(this.feePayment));
        final FeePayment retrievedFeePayment = this.feePaymentService.findById(this.feePayment.getId());
        assertNotNull(retrievedFeePayment);
        assertEquals("first Term", retrievedFeePayment.getFee_Term());
        assertEquals("paid", retrievedFeePayment.getStatus());
        assertEquals(2500.0, retrievedFeePayment.getAmount());
        assertEquals(Timestamp.valueOf("2021-08-09 05:30:00"), retrievedFeePayment.getDate());
        assertEquals(1, retrievedFeePayment.getStudent().getId());
        verify(this.feePaymentRepository, times(1)).findById(this.feePayment.getId());
    }

    @Test
    void testRetrieveAll() {
        when(this.feePaymentRepository.findAll()).thenReturn(List.of(this.feePayment));
        final List<FeePayment> feePayment = this.feePaymentService.getAllFeePayment();
        assertNotNull(feePayment);
        assertFalse(feePayment.isEmpty());
        assertEquals(1, feePayment.size());
        verify(this.feePaymentRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteById() {
        when(this.feePaymentRepository.existsById(1)).thenReturn(true);
        doNothing().when(this.feePaymentRepository).deleteById(1);

        final String result = this.feePaymentService.deleteById(1);

        assertEquals(Constant.REMOVE, result);
        verify(this.feePaymentRepository, times(1)).existsById(1);
        verify(this.feePaymentRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteByIdNotFound() {
        when(this.feePaymentRepository.existsById(any(Integer.class))).thenReturn(false);
        final UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            this.feePaymentService.deleteById(this.feePayment.getId());
        });
        assertEquals(Constant.NOT_FOUND + " " + this.feePayment.getId(), exception.getMessage());
    }

    @Test
    public void testUpdateById() {

        final FeePayment existingFeePayment = new FeePayment();
        existingFeePayment.setFee_Term("first Term");
        existingFeePayment.setStatus("paid");
        existingFeePayment.setAmount(2500.0);

        when(this.feePaymentRepository.findById(any(Integer.class))).thenReturn(Optional.of(this.feePayment));
        when(this.feePaymentRepository.save(any(FeePayment.class))).thenReturn(existingFeePayment);

        final FeePayment result = this.feePaymentService.updateById(existingFeePayment, this.feePayment.getId());
        assertNotNull(result);
        assertEquals("first Term", result.getFee_Term());
        assertEquals("paid", result.getStatus());
        assertEquals(2500.0, result.getAmount());

        verify(this.feePaymentRepository, times(1)).findById(this.feePayment.getId());
        verify(this.feePaymentRepository, times(1)).save(any(FeePayment.class));
    }

    @Test
    public void testUpdateByIdNotFound() {
        int feePaymentId = 1;
        final FeePayment updatedData = new FeePayment();
        updatedData.setFee_Term("first Term");
        updatedData.setStatus("paid");
        updatedData.setAmount(2500.0);
        when(this.feePaymentRepository.findById(any(Integer.class))).thenReturn(Optional.empty());

        final UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            this.feePaymentService.updateById(updatedData, feePaymentId);
        });

        assertEquals(Constant.ID_DOES_NOT_EXIST, exception.getMessage());
        verify(this.feePaymentRepository, never()).save(any());
    }
    @Test
    void testGetFeePayment() {

        final School school = new School();
        school.setId(1);
        school.setName("SRM School");

        final School school1 = new School();
        school1.setId(2);
        school1.setName("AKT School");
        Student student = new Student();
        student.setId(1);
        student.setSchool(school);

        final Student student2 = new Student();
        student2.setId(1);
        student2.setSchool(school);

         final Student student3 = new Student();
        student3.setId(2);
        student3.setSchool(school1);

        final FeePayment payment = new FeePayment();
        payment.setStudent(student);
        payment.setAmount(2000.0);

        final FeePayment payment1 = new FeePayment();
        payment1.setStudent(student2);
        payment1.setAmount(3000.0);

        final FeePayment payment2 = new FeePayment();
        payment2.setStudent(student3);
        payment2.setAmount(1500.0);

        final List<FeePayment> payments = Arrays.asList(payment, payment1, payment2);
        when(this.feePaymentRepository.findAllFeePayments()).thenReturn(payments);

        final List<SchoolFeeDetailsDTO> result = this.feePaymentService.getfeepayment();

        assertEquals(2, result.size());

        final SchoolFeeDetailsDTO dto1 = result.stream()
                .filter(dto -> dto.getSchoolId() == 1)
                .findFirst()
                .orElse(null);
        assertNotNull(dto1);
        assertEquals("SRM School", dto1.getSchoolName());
        assertEquals(2000.0, dto1.getMinimumFee());
        assertEquals(3000.0, dto1.getMaximumFee());

        final SchoolFeeDetailsDTO dto2 = result.stream()
                .filter(dto -> dto.getSchoolId() == 2)
                .findFirst()
                .orElse(null);
        assertNotNull(dto2);
        assertEquals("AKT School", dto2.getSchoolName());
        assertEquals(1500.0, dto2.getMinimumFee());
        assertEquals(1500.0, dto2.getMaximumFee());

        verify(this.feePaymentRepository, times(1)).findAllFeePayments();}
}
