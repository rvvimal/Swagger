package com.school_management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school_management.dto.SchoolDTO;
import com.school_management.dto.SchoolDetailsDTO;
import com.school_management.entity.Course;
import com.school_management.entity.StudentCourse;
import com.school_management.entity.Tutor;
import com.school_management.service.StudentCourseService;
import com.school_management.util.Constant;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
public class StudentCourseControllerTest {


    @Mock
    private StudentCourseService studentCourseService;
    @InjectMocks
    private StudentCourseController studentCourseController;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private StudentCourse studentCourse;

    @BeforeAll
    public static void toStartStudentCourseController() {
        System.out.println("StudentCourse Controller Test case execution has been started");
    }

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(studentCourseController).build();
        this.studentCourse = new StudentCourse();
        final Tutor tutor = new Tutor();
        final Course course = new Course();
        this.studentCourse.setId(1);
        this.studentCourse.setTutor(tutor);
        this.studentCourse.setCourse(course);
    }

    @Test
    public void testCreateStudentCourse() throws Exception {
        when(this.studentCourseService.createStudentCourse(any(StudentCourse.class))).thenReturn(this.studentCourse);

        this.mockMvc.perform(post("/api/v1/studentCourse")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(this.studentCourse)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusCode").value(201))
                .andExpect(jsonPath("$.message").value(Constant.CREATE));
        verify(this.studentCourseService, times(1)).createStudentCourse(any(StudentCourse.class));
    }

    @Test
    public void testGetAllStudentCourse() throws Exception {
        when(this.studentCourseService.getAllStudentCourse()).thenReturn(Collections.emptyList());

        this.mockMvc.perform(get("/api/v1/studentCourse")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(this.studentCourse)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(200))
                .andExpect(jsonPath("$.message").value(Constant.RETRIEVE));
        verify(this.studentCourseService, times(1)).getAllStudentCourse();
    }

    @Test
    public void testGetStudentCourseById() throws Exception {
        when(this.studentCourseService.findById(1)).thenReturn(this.studentCourse);

        this.mockMvc.perform(get("/api/v1/studentCourse/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(this.studentCourse)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(200))
                .andExpect(jsonPath("$.message").value(Constant.RETRIEVE));
        verify(this.studentCourseService, times(1)).findById(this.studentCourse.getId());
    }

    @Test
    public void testRetrieveAllStudentCourseDetail() throws Exception {
        when(this.studentCourseService.retrieveAllStudentDetail()).thenReturn(Collections.emptyList());

        this.mockMvc.perform(get("/api/v1/studentCourse/retrieve-all-student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(this.studentCourse)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(200))
                .andExpect(jsonPath("$.message").value(Constant.RETRIEVE));
    }

    @Test
    public void testDeleteStudentCourseById() throws Exception {
        when(this.studentCourseService.deleteById(1)).thenReturn("Deleted");

        this.mockMvc.perform(delete("/api/v1/studentCourse/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(this.studentCourse)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(200))
                .andExpect(jsonPath("$.message").value(Constant.DELETE));
        verify(this.studentCourseService, times(1)).deleteById(1);
    }

    @Test
    public void testStudentCoursePages() throws Exception {
        final Page<SchoolDetailsDTO> page = new PageImpl<>(Collections.emptyList(), PageRequest.of(0, 5), 0);
        when(this.studentCourseService.getSchoolPages(1, 0, 5)).thenReturn(page);

        this.mockMvc.perform(get("/api/v1/studentCourse/1/page")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(this.studentCourse))
                        .param("pageIndex", "0")
                        .param("pageSize", "5"))
                .andExpect(status().isOk());
        verify(this.studentCourseService, times(1)).getSchoolPages(this.studentCourse.getId(), 0,5);
    }

    @Test
    public void testStudentCourseCount() throws Exception {
        SchoolDTO schoolDTO = new SchoolDTO();
        when(this.studentCourseService.getCourseCount(1)).thenReturn(schoolDTO);

        this.mockMvc.perform(get("/api/v1/studentCourse/1/countCourse")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(this.studentCourse)))
                .andExpect(status().isOk());
    }

    @AfterAll
    public static void endStudentCourseControllerTest() {
        System.out.println("StudentCourse Controller Test execution finished");
    }
}
