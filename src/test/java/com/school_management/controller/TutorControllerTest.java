package com.school_management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school_management.entity.Tutor;
import com.school_management.service.TutorService;
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

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class TutorControllerTest {
    @Mock
    private TutorService tutorService;
    @InjectMocks
    private TutorController tutorController;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Tutor tutor;

    @BeforeAll
    public static void toStartTutorController() {
        System.out.println("Tutor Controller Test case execution has been started");
    }

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(tutorController).build();
        this.tutor = new Tutor();
        this.tutor.setId(1);
        this.tutor.setName("Ravi");
        this.tutor.setEmail("ravi23@gmail.com");
        this.tutor.setContactNumber(9553037081L);

    }

    @Test
    public void testCreateTutor() throws Exception {
        when(this.tutorService.createTutor(any(Tutor.class))).thenReturn(this.tutor);
        this.mockMvc.perform(post("/api/v1/tutor")
                        .contentType("application/json")
                        .content(this.objectMapper.writeValueAsString(this.tutor)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.CREATED.value()))
                .andExpect(jsonPath("$.message").value(Constant.CREATE))
                .andExpect(jsonPath("$.data.name").value("Ravi"))
                .andExpect(jsonPath("$.data.contactNumber").value(9553037081L));
        verify(this.tutorService, times(1)).createTutor(any(Tutor.class));
    }

    @Test
    public void testTutorById() throws Exception {
        when(this.tutorService.findById(1)).thenReturn(this.tutor);
        this.mockMvc.perform(get("/api/v1/tutor/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value(Constant.RETRIEVE))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.name").value("Ravi"));
        verify(this.tutorService, times(1)).findById(this.tutor.getId());
    }

    @Test
    public void testTutorGetAll() throws Exception {
        when(this.tutorService.getAlltutor()).thenReturn(List.of(this.tutor));
        this.mockMvc.perform(get("/api/v1/tutor"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value(Constant.RETRIEVE))
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[0].name").value("Ravi"));
        verify(this.tutorService, times(1)).getAlltutor();
    }

    @Test
    public void testUpdateById() throws Exception {
        when(this.tutorService.updateById(any(Tutor.class), any(Integer.class))).thenReturn(this.tutor);
        this.mockMvc.perform(put("/api/v1/tutor/1")
                        .contentType("application/json")
                        .content(this.objectMapper.writeValueAsString(this.tutor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value(Constant.UPDATE))
                .andExpect(jsonPath("$.data.id").value("1"))
                .andExpect(jsonPath("$.data.name").value("Ravi"));
    }

    @Test
    public void testDeleteById() throws Exception {
        when(this.tutorService.deleteById(1)).thenReturn(String.valueOf(true));
        this.mockMvc.perform(delete("/api/v1/tutor/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(this.tutor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value(Constant.DELETE))
                .andExpect(jsonPath("$.data").value("true"));
        verify(this.tutorService, times(1)).deleteById(1);
    }

    @AfterAll
    public static void endTutorControllerTest() {
        System.out.println("Tutor Controller Test execution finished");
    }
}
