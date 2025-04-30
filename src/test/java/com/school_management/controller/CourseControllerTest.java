package com.school_management.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.school_management.entity.Course;
import com.school_management.service.CourseService;
import com.school_management.util.Constant;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CourseControllerTest {
    @Mock
    private CourseService courseService;
    @InjectMocks
    private CourseController courseController;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Course course;

    @BeforeAll
    public static void toStartCourseController() {
        System.out.println("Course Controller Test case execution has been started");
    }

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(courseController).build();
        this.course = new Course();
        this.course.setId(1);
        this.course.setName("Tamil");
    }

    @Test
    public void testCreateCourse() throws Exception {
        Mockito.when(this.courseService.createCourse(any(Course.class))).thenReturn(this.course);

        this.mockMvc.perform(post("/api/v1/course")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(this.course)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.CREATED.value()))
                .andExpect(jsonPath("$.message").value(Constant.CREATE))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.name").value("Tamil"));
        verify(this.courseService, times(1)).createCourse(any(Course.class));
    }

    @Test
    public void testGetAllCourses() throws Exception {
        Mockito.when(this.courseService.getAllCourse()).thenReturn(java.util.List.of(this.course));

        this.mockMvc.perform(get("/api/v1/course"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value(Constant.RETRIEVE))
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[0].name").value("Tamil"));
        verify(this.courseService, times(1)).getAllCourse();
    }

    @Test
    public void testGetCourseById() throws Exception {
        Mockito.when(this.courseService.findById(1)).thenReturn(this.course);

        this.mockMvc.perform(get("/api/v1/course/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value(Constant.RETRIEVE))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.name").value("Tamil"));
        verify(this.courseService, times(1)).findById(course.getId());
    }

    @Test
    public void testUpdateById() throws Exception {
        when(this.courseService.updateById(any(Course.class), any(Integer.class))).thenReturn(this.course);
        this.mockMvc.perform(put("/api/v1/course/1")
                        .contentType("application/json")
                        .content(this.objectMapper.writeValueAsString(this.course)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value(Constant.UPDATE))
                .andExpect(jsonPath("$.data.id").value("1"))
                .andExpect(jsonPath("$.data.name").value("Tamil"));
    }

    @Test
    public void testDeleteById() throws Exception {
        when(this.courseService.deleteById(1)).thenReturn(String.valueOf(true));
        this.mockMvc.perform(delete("/api/v1/course/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(this.course)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value(Constant.DELETE))
                .andExpect(jsonPath("$.data").value("true"));
        verify(this.courseService, times(1)).deleteById(1);
    }

    @AfterAll
    public static void endCourseControllerTest() {
        System.out.println("Course Controller Test execution finished");
    }
}
