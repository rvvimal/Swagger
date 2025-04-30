package com.school_management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school_management.dto.TutorSalaryDTO;
import com.school_management.entity.Tutor;
import com.school_management.entity.TutorSalary;
import com.school_management.service.TutorSalaryService;
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

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class TutorSalaryControllerTest {
    @Mock
    private TutorSalaryService tutorSalaryService;
    @InjectMocks
    private TutorSalaryController tutorSalaryController;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private TutorSalary tutorSalary;

    @BeforeAll
    public static void toStartTutorSalaryController() {
        System.out.println("TutorSalary Controller Test case execution has been started");
    }

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(tutorSalaryController).build();
        final Tutor tutor = new Tutor();
        this.tutorSalary = new TutorSalary();
        this.tutorSalary.setId(1);
        this.tutorSalary.setPaid("paid");
        this.tutorSalary.setAmount(4000.0);
        this.tutorSalary.setMonth("June");
        this.tutorSalary.setTutor(tutor);
    }

    @Test
    public void testCreateTutorSalary() throws Exception {
        when(this.tutorSalaryService.createTutorSalary(any(TutorSalary.class))).thenReturn(this.tutorSalary);
        this.mockMvc.perform(post("/api/v1/tutorSalary")
                        .contentType("application/json")
                        .content(this.objectMapper.writeValueAsString(this.tutorSalary)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.CREATED.value()))
                .andExpect(jsonPath("$.message").value(Constant.CREATE))
                .andExpect(jsonPath("$.data.paid").value("paid"))
                .andExpect(jsonPath("$.data.month").value("June"))
                .andExpect(jsonPath("$.data.amount").value(4000.0));
        verify(this.tutorSalaryService, times(1)).createTutorSalary(any(TutorSalary.class));
    }

    @Test
    public void testTutorSalaryById() throws Exception {
        when(this.tutorSalaryService.findById(1)).thenReturn(this.tutorSalary);
        this.mockMvc.perform(get("/api/v1/tutorSalary/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value(Constant.RETRIEVE))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.paid").value("paid"))
                .andExpect(jsonPath("$.data.month").value("June"))
                .andExpect(jsonPath("$.data.amount").value(4000.0));
        verify(this.tutorSalaryService, times(1)).findById(this.tutorSalary.getId());
    }

    @Test
    public void testTutorSalaryGetAll() throws Exception {
        when(this.tutorSalaryService.getAlltutorSalary()).thenReturn(List.of(this.tutorSalary));
        this.mockMvc.perform(get("/api/v1/tutorSalary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value(Constant.RETRIEVE))
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[0].paid").value("paid"))
                .andExpect(jsonPath("$.data[0].month").value("June"))
                .andExpect(jsonPath("$.data[0].amount").value(4000.0));
        verify(this.tutorSalaryService, times(1)).getAlltutorSalary();
    }

    @Test
    public void testUpdateById() throws Exception {
        when(this.tutorSalaryService.updateById(any(TutorSalary.class), any(Integer.class))).thenReturn(this.tutorSalary);
        this.mockMvc.perform(put("/api/v1/tutorSalary/1")
                        .contentType("application/json")
                        .content(this.objectMapper.writeValueAsString(this.tutorSalary)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value(Constant.UPDATE))
                .andExpect(jsonPath("$.data.id").value("1"))
                .andExpect(jsonPath("$.data.paid").value("paid"))
                .andExpect(jsonPath("$.data.month").value("June"))
                .andExpect(jsonPath("$.data.amount").value(4000.0));
    }

    @Test
    public void testDeleteById() throws Exception {
        when(this.tutorSalaryService.deleteById(1)).thenReturn(String.valueOf(true));
        this.mockMvc.perform(delete("/api/v1/tutorSalary/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(this.tutorSalary)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value(Constant.DELETE))
                .andExpect(jsonPath("$.data").value("true"));
        verify(this.tutorSalaryService, times(1)).deleteById(1);
    }
    @Test
    public void testGetTutorAmountById() throws Exception {
        final TutorSalaryDTO dto = new TutorSalaryDTO();
        dto.setTutorName("Ravi");
        dto.setAmount(4500.0);
        dto.setTutorId(1);
        final List<TutorSalaryDTO> mockList = Arrays.asList(dto);

        when(this.tutorSalaryService.getFeeAmount(1)).thenReturn(mockList);

        this.mockMvc.perform(get("/api/v1/tutorSalary/1/tutorAmount"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].tutorId").value(1))
                .andExpect(jsonPath("$[0].tutorName").value("Ravi"))
                .andExpect(jsonPath("$[0].amount").value(4500.0));

        verify(this.tutorSalaryService, times(1)).getFeeAmount(1);
    }

    @AfterAll
    public static void endTutorSalaryControllerTest() {
        System.out.println("TutorSalary Controller Test execution finished");
    }
}
