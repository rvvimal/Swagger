package com.school_management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school_management.entity.Course;
import com.school_management.entity.TutorCourse;
import com.school_management.service.TutorCourseService;
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

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class TutorCourseControllerTest {
    @Mock
    private TutorCourseService tutorCourseService;
    @InjectMocks
    private TutorCourseController tutorCourseController;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private TutorCourse tutorCourse;

    @BeforeAll
    public static void toStartTutorCourseController() {
        System.out.println("TutorCourse Controller Test case execution has been started");
    }

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(tutorCourseController).build();
        this.tutorCourse = new TutorCourse();
        final Course course = new Course();
        this.tutorCourse.setId(1);
        this.tutorCourse.setCourse(course);
    }

    @Test
    public void testCreateTutorCourse() throws Exception {
        when(this.tutorCourseService.createTutorCourse(any(TutorCourse.class))).thenReturn(this.tutorCourse);

        this.mockMvc.perform(post("/api/v1/tutorCourse")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(this.tutorCourse)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.CREATED.value()))
                .andExpect(jsonPath("$.message").value(Constant.CREATE));

        verify(this.tutorCourseService, times(1)).createTutorCourse(any(TutorCourse.class));
    }

    @Test
    public void testGetAllTutorCourses() throws Exception {
        when(this.tutorCourseService.getAlltutorCourse()).thenReturn(Collections.singletonList(this.tutorCourse));

        this.mockMvc.perform(get("/api/v1/tutorCourse"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value(Constant.RETRIEVE));

        verify(this.tutorCourseService, times(1)).getAlltutorCourse();
    }

    @Test
    public void testGetTutorCourseById() throws Exception {
        when(this.tutorCourseService.findById(1)).thenReturn(this.tutorCourse);

        this.mockMvc.perform(get("/api/v1/tutorCourse/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value(Constant.RETRIEVE))
                .andExpect(jsonPath("$.data.id").value(1));

        verify(this.tutorCourseService, times(1)).findById(1);
    }

    @Test
    public void testDeleteTutorCourseById() throws Exception {
        when(this.tutorCourseService.deleteById(1)).thenReturn("Deleted");

        this.mockMvc.perform(delete("/api/v1/tutorCourse/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value(Constant.DELETE));

        verify(this.tutorCourseService, times(1)).deleteById(1);
    }
    @AfterAll
    public static void endTutorCourseControllerTest() {
        System.out.println("TutorCourse Controller Test execution finished");
    }
}
