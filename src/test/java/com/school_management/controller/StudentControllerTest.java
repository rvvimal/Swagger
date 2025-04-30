package com.school_management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school_management.dto.PaginationResponse;
import com.school_management.dto.StudentDetailsDTO;
import com.school_management.entity.Student;
import com.school_management.service.StudentService;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {
    @Mock
    private StudentService studentService;
    @InjectMocks
    private StudentController studentController;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Student student;

    @BeforeAll
    public static void toStartStudentController() {
        System.out.println("Student Controller Test case execution has been started");
    }

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
        this.student = new Student();
        this.student.setId(1);
        this.student.setFirstName("Kavinkumar");
        this.student.setLastName("G");
        this.student.setEmail("kavikumar@gmail.com");
        this.student.setContactNumber(9543631987L);
        this.student.setEnrollmentDate("25.06.2025");

    }

    @Test
    public void testCreateStudent() throws Exception {
        when(this.studentService.createStudent(any(Student.class))).thenReturn(this.student);
        this.mockMvc.perform(post("/api/v1/student")
                        .contentType("application/json")
                        .content(this.objectMapper.writeValueAsString(this.student)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.CREATED.value()))
                .andExpect(jsonPath("$.message").value(Constant.CREATE))
                .andExpect(jsonPath("$.data.firstName").value("Kavinkumar"));
        verify(this.studentService, times(1)).createStudent(any(Student.class));
    }

    @Test
    public void testStudentById() throws Exception {
        when(this.studentService.findById(1)).thenReturn(this.student);
        this.mockMvc.perform(get("/api/v1/student/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value(Constant.RETRIEVE))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.firstName").value("Kavinkumar"));
        verify(this.studentService, times(1)).findById(this.student.getId());
    }

    @Test
    public void testStudentGetAll() throws Exception {
        when(this.studentService.getAllStudent()).thenReturn(List.of(this.student));
        this.mockMvc.perform(get("/api/v1/student"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value(Constant.RETRIEVE))
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[0].firstName").value("Kavinkumar"));
        verify(this.studentService, times(1)).getAllStudent();
    }

    @Test
    public void testUpdateById() throws Exception {
        when(this.studentService.updateById(any(Student.class), any(Integer.class))).thenReturn(this.student);
        this.mockMvc.perform(put("/api/v1/student/1")
                        .contentType("application/json")
                        .content(this.objectMapper.writeValueAsString(this.student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value(Constant.UPDATE))
                .andExpect(jsonPath("$.data.id").value("1"))
                .andExpect(jsonPath("$.data.firstName").value("Kavinkumar"));
    }

    @Test
    public void testDeleteById() throws Exception {
        when(this.studentService.deleteById(1)).thenReturn(String.valueOf(true));
        this.mockMvc.perform(delete("/api/v1/student/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(this.student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value(Constant.DELETE))
                .andExpect(jsonPath("$.data").value("true"));
        verify(this.studentService, times(1)).deleteById(1);
    }

    @Test
    public void testGetStudentCoursePage() throws Exception {
        final PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setDetails(Collections.emptyList());
        paginationResponse.setPageSize(5);
        paginationResponse.setTotalPages(0);
        paginationResponse.setTotalElements(10);

        when(this.studentService.getSortedStudentPage(0, 5, "id", true)).thenReturn(paginationResponse);
        this.mockMvc.perform(get("/api/v1/student/page/sorted")
                        .param("pageIndex", "0")
                        .param("pageSize", "5")
                        .param("field", "id")
                        .param("sort", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value(Constant.RETRIEVE));

        verify(this.studentService, times(1)).getSortedStudentPage(0, 5, "id", true);
    }

    @Test
    public void testGetStudentWithCourses() throws Exception {
        List<StudentDetailsDTO> mockResponse = new ArrayList<>();
        mockResponse.add(new StudentDetailsDTO(1, "Kavinkumar", "Mathematics"));
        mockResponse.add(new StudentDetailsDTO(1, "Kavinkumar", "Science"));

        when(this.studentService.getStudentWithCourses(1)).thenReturn(mockResponse);

        this.mockMvc.perform(get("/api/v1/student/1/courses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value(Constant.RETRIEVE))
                .andExpect(jsonPath("$.data[0].studentId").value(1))
                .andExpect(jsonPath("$.data[0].studentName").value("Kavinkumar"))
                .andExpect(jsonPath("$.data[0].courseName").value("Mathematics"))
                .andExpect(jsonPath("$.data[1].courseName").value("Science"));

        verify(this.studentService, times(1)).getStudentWithCourses(1);
    }


    @AfterAll
    public static void endStudentControllerTest() {
        System.out.println("Student Controller Test execution finished");
    }
}
