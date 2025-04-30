package com.school_management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school_management.dto.SchoolFeeDetailsDTO;
import com.school_management.entity.FeePayment;
import com.school_management.entity.Student;
import com.school_management.service.FeePaymentService;
import com.school_management.util.Constant;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class FeePaymentControllerTest {
    @Mock
    private FeePaymentService feePaymentService;
    @InjectMocks
    private FeePaymentController feePaymentController;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private FeePayment feePayment;

    @BeforeAll
    public static void toStartFeePaymentController() {
        System.out.println("FeePayment Controller Test case execution has been started");
    }

    @BeforeEach
    public void setUp() throws ParseException {
        this.mockMvc = MockMvcBuilders.standaloneSetup(feePaymentController).build();
        final Student student = new Student();
        this.feePayment = new FeePayment();
        this.feePayment.setId(1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date paymentDate = formatter.parse("2021-08-09 05:30:00");
        this.feePayment.setDate(paymentDate);
        this.feePayment.setFee_Term("first term");
        this.feePayment.setAmount(2500);
        this.feePayment.setStatus("paid");
        this.feePayment.setStudent(student);
    }

    @Test
    public void testCreateSchool() throws Exception {
        when(this.feePaymentService.createFeePayment(any(FeePayment.class))).thenReturn(this.feePayment);
        this.mockMvc.perform(post("/api/v1/feePayment")
                        .contentType("application/json")
                        .content(this.objectMapper.writeValueAsString(this.feePayment)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.CREATED.value()))
                .andExpect(jsonPath("$.message").value(Constant.CREATE))
                .andExpect(jsonPath("$.data.fee_Term").value("first term"))
                .andExpect(jsonPath("$.data.amount").value(2500))
                .andExpect(jsonPath("$.data.status").value("paid"));
        verify(this.feePaymentService, times(1)).createFeePayment(any(FeePayment.class));
    }

    @Test
    public void testFeePaymentById() throws Exception {
        when(this.feePaymentService.findById(1)).thenReturn(this.feePayment);
        this.mockMvc.perform(get("/api/v1/feePayment/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value(Constant.RETRIEVE))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.fee_Term").value("first term"))
                .andExpect(jsonPath("$.data.amount").value(2500))
                .andExpect(jsonPath("$.data.status").value("paid"));
        verify(this.feePaymentService, times(1)).findById(this.feePayment.getId());
    }

    @Test
    public void testFeePaymentGetAll() throws Exception {
        when(this.feePaymentService.getAllFeePayment()).thenReturn(List.of(this.feePayment));
        this.mockMvc.perform(get("/api/v1/feePayment"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value(Constant.RETRIEVE))
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[0].fee_Term").value("first term"))
                .andExpect(jsonPath("$.data[0].amount").value(2500))
                .andExpect(jsonPath("$.data[0].status").value("paid"));
        verify(this.feePaymentService, times(1)).getAllFeePayment();
    }

    @Test
    public void testUpdateById() throws Exception {
        when(this.feePaymentService.updateById(any(FeePayment.class), any(Integer.class))).thenReturn(this.feePayment);
        this.mockMvc.perform(put("/api/v1/feePayment/1")
                        .contentType("application/json")
                        .content(this.objectMapper.writeValueAsString(this.feePayment)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value(Constant.UPDATE))
                .andExpect(jsonPath("$.data.id").value("1"))
                .andExpect(jsonPath("$.data.fee_Term").value("first term"))
                .andExpect(jsonPath("$.data.amount").value(2500))
                .andExpect(jsonPath("$.data.status").value("paid"));
    }

    @Test
    public void testDeleteById() throws Exception {
        when(this.feePaymentService.deleteById(1)).thenReturn(String.valueOf(true));
        this.mockMvc.perform(delete("/api/v1/feePayment/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(this.feePayment)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value(Constant.DELETE))
                .andExpect(jsonPath("$.data").value("true"));
        verify(this.feePaymentService, times(1)).deleteById(1);
    }

    @Test
    public void testGetFeePaymentDetails() throws Exception {
        final SchoolFeeDetailsDTO dto = new SchoolFeeDetailsDTO();
        dto.setSchoolName("SRM School");
        dto.setMaximumFee(2500.0);
        dto.setMinimumFee(3500.0);

        final SchoolFeeDetailsDTO dtos = new SchoolFeeDetailsDTO();
        dtos.setSchoolName("AKT School");
        dtos.setMinimumFee(4500.0);
        dtos.setMaximumFee(5500.0);
        final List<SchoolFeeDetailsDTO> mockList = Arrays.asList(dto, dtos);
        when(this.feePaymentService.getfeepayment()).thenReturn(mockList);

        this.mockMvc.perform(get("/api/v1/feePayment/feeDetails"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].schoolName").value("SRM School"))
                .andExpect(jsonPath("$[0].minimumFee").value(3500.0))
                .andExpect(jsonPath("$[0].maximumFee").value(2500.0))
                .andExpect(jsonPath("$[1].schoolName").value("AKT School"))
                .andExpect(jsonPath("$[1].minimumFee").value(4500.0))
                .andExpect(jsonPath("$[1].maximumFee").value(5500.0));

        verify(this.feePaymentService, times(1)).getfeepayment();
    }

    @AfterAll
    public static void endFeePaymentControllerTest() {
        System.out.println("FeePayment Controller Test execution finished");
    }
}
