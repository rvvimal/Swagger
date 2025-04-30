package com.school_management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school_management.entity.School;
import com.school_management.service.SchoolService;
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
public class SchoolControllerTest {
    @Mock
    private SchoolService schoolService;
    @InjectMocks
    private SchoolController schoolController;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private School school;

    @BeforeAll
    public static void toStartSchoolController() {
        System.out.println("School Controller Test case execution has been started");
    }

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(schoolController).build();
        this.school = new School();
        this.school.setId(1);
        this.school.setName("SRM School");
        this.school.setAddress("Kallakurichi");
        this.school.setContact(7942699116L);
    }

    @Test
    public void testCreateSchool() throws Exception {
        when(this.schoolService.createSchool(any(School.class))).thenReturn(this.school);
        this.mockMvc.perform(post("/api/v1/school")
                        .contentType("application/json")
                        .content(this.objectMapper.writeValueAsString(this.school)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.CREATED.value()))
                .andExpect(jsonPath("$.message").value(Constant.CREATE))
                .andExpect(jsonPath("$.data.name").value("SRM School"))
                .andExpect(jsonPath("$.data.contact").value(7942699116L));
        verify(this.schoolService, times(1)).createSchool(any(School.class));
    }

    @Test
    public void testSchoolById() throws Exception {
        when(this.schoolService.findById(1)).thenReturn(this.school);
        this.mockMvc.perform(get("/api/v1/school/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value(Constant.RETRIEVE))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.name").value("SRM School"));
        verify(this.schoolService, times(1)).findById(this.school.getId());
    }

    @Test
    public void testSchoolGetAll() throws Exception {
        when(this.schoolService.getAllSchool()).thenReturn(List.of(this.school));
        this.mockMvc.perform(get("/api/v1/school"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value(Constant.RETRIEVE))
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[0].name").value("SRM School"));
        verify(this.schoolService, times(1)).getAllSchool();
    }

    @Test
    public void testUpdateById() throws Exception {
        when(this.schoolService.updateById(any(School.class), any(Integer.class))).thenReturn(this.school);
        this.mockMvc.perform(put("/api/v1/school/1")
            .contentType("application/json")
            .content(this.objectMapper.writeValueAsString(this.school)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
            .andExpect(jsonPath("$.message").value(Constant.UPDATE))
            .andExpect(jsonPath("$.data.id").value("1"))
            .andExpect(jsonPath("$.data.name").value("SRM School"));
    }
    @Test
    public void testDeleteById() throws Exception {
        when(this.schoolService.deleteById(1)).thenReturn(String.valueOf(true));
        this.mockMvc.perform(delete("/api/v1/school/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(this.school)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value(Constant.DELETE))
                .andExpect(jsonPath("$.data").value("true"));
        verify(this.schoolService, times(1)).deleteById(1);
    }
    @AfterAll
    public static void endSchoolControllerTest(){
        System.out.println("School Controller Test execution finished");
    }
}
