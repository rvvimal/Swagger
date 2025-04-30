package com.school_management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school_management.dto.SignInRequestDTO;
import com.school_management.dto.SignUpRequestDTO;
import com.school_management.entity.User;
import com.school_management.enums.Role;
import com.school_management.service.UserService;
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
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private User user;

    @BeforeAll
    public static void toStartUserController() {
        System.out.println("User Controller Test case execution has been started");
    }

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

    }

    @Test
    public void testCreateAdmin() throws Exception {
        final SignUpRequestDTO signUpRequestDTO = new SignUpRequestDTO();
        signUpRequestDTO.setUserName("Ravi");
        signUpRequestDTO.setEmailId("ravi12@gmail.com");
        signUpRequestDTO.setPassword("$2a$12$Mir1f4z6XtY65J6S8.6Nwuv.g5r5U5xFDmSg.J9XqvZUYkq9miihC");
        final User user = new User();
        user.setEmailId("ravi12@gmail.com");
        user.setRole(Role.ADMIN);
        when(userService.adminCreate(any(SignUpRequestDTO.class))).thenReturn(user);

        this.mockMvc.perform(post("/api/v1/auth/admin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(signUpRequestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.emailId").value(user.getEmailId()))
                .andExpect(jsonPath("$.data.role").value(user.getRole().toString()));
        verify(this.userService).adminCreate(any(SignUpRequestDTO.class));
    }

    @Test
    public void testCreateTeacher() throws Exception {
        final SignUpRequestDTO signUpRequestDTO = new SignUpRequestDTO();
        signUpRequestDTO.setUserName("Rajesh");
        signUpRequestDTO.setEmailId("rajesh23@gmail.com");
        signUpRequestDTO.setPassword("$2a$12$xeHKrxkZ3glkmd5zR4q2XeN36xdcGnlD6UXiH0K/Jx/JE.CajEj9K");
        User user = new User();
        user.setEmailId("rajesh23@gmail.com");
        user.setRole(Role.TEACHER);
        when(this.userService.teacherCreate(any(SignUpRequestDTO.class))).thenReturn(user);

        this.mockMvc.perform(post("/api/v1/auth/teacher")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(signUpRequestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.emailId").value(user.getEmailId()))
                .andExpect(jsonPath("$.data.role").value(user.getRole().toString()));
        verify(this.userService).teacherCreate(any(SignUpRequestDTO.class));
    }

    @Test
    public void testCreateStudent() throws Exception {
        final SignUpRequestDTO signUpRequestDTO = new SignUpRequestDTO();
        signUpRequestDTO.setUserName("Kavinkumar");
        signUpRequestDTO.setEmailId("kavikumar@gmail.com");
        signUpRequestDTO.setPassword("$2a$12$hn8HI0qg5EsML83NoHsFhu/06jRbZKiV/IOiYa8Q6Bk0uQcaCZtFO");
        User user = new User();
        user.setEmailId("kavikumar@gmail.com");
        user.setRole(Role.STUDENT);
        when(this.userService.studentCreate(any(SignUpRequestDTO.class))).thenReturn(user);

        this.mockMvc.perform(post("/api/v1/auth/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(signUpRequestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.emailId").value(user.getEmailId()))
                .andExpect(jsonPath("$.data.role").value(user.getRole().toString()));
        verify(this.userService).studentCreate(any(SignUpRequestDTO.class));
    }

    @Test
    public void testSignLogin() throws Exception {
        final SignInRequestDTO requestDTO = new SignInRequestDTO();
        requestDTO.setEmailId("ravi12@gmail.com");
        requestDTO.setPassword("$2a$12$Mir1f4z6XtY65J6S8.6Nwuv.g5r5U5xFDmSg.J9XqvZUYkq9miihC");

        when(this.userService.signIn(any(SignInRequestDTO.class)))
                .thenReturn(Map.of("message", "Login Successful"));

        this.mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusCode").value(201))
                .andExpect(jsonPath("$.message").value(Constant.TOKEN))
                .andExpect(jsonPath("$.data.message").value("Login Successful"));

        verify(this.userService).signIn(any(SignInRequestDTO.class));
    }

    @Test
    public void testRefreshToken() throws Exception {
        when(this.userService.refreshToken(any(String.class)))
                .thenReturn(Map.of("Token", "newToken"));
        this.mockMvc.perform(post("/api/v1/auth/refresh")
                        .param("refreshToken", "RefreshToken"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.CREATED.value()))
                .andExpect(jsonPath("$.message").value(Constant.TOKEN))
                .andExpect(jsonPath("$.data.Token").value("newToken"));
        verify(this.userService).refreshToken(any(String.class));
    }

    @Test
    public void testRetrieveById() throws Exception {
        this.user = new User();
        this.user.setId(1);
        this.user.setName("Ravi");
        this.user.setEmailId("ravi12@gmail.com");
        this.user.setPassword("$2a$12$Mir1f4z6XtY65J6S8.6Nwuv.g5r5U5xFDmSg.J9XqvZUYkq9miihC");
        this.user.setRole(Role.ADMIN);
        when(this.userService.getById(1)).thenReturn(user);
        this.mockMvc.perform(get("/api/v1/admin/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(this.user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value(Constant.RETRIEVE))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.name").value("Ravi"));
    }

    @Test
    public void testRetrieveAll() throws Exception {
        this.user = new User();
        this.user.setId(1);
        this.user.setName("Ravi");
        this.user.setEmailId("ravi12@gmail.com");
        this.user.setPassword("$2a$12$Mir1f4z6XtY65J6S8.6Nwuv.g5r5U5xFDmSg.J9XqvZUYkq9miihC");
        this.user.setRole(Role.ADMIN);
        when(this.userService.findByAllAndId()).thenReturn(List.of(this.user));
        this.mockMvc.perform(get("/api/v1/admin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(this.user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value(Constant.RETRIEVE))
                .andExpect(jsonPath("$.data[0].name").value("Ravi"));
    }

    @Test
    public void testDeleteById() throws Exception {
        this.user = new User();
        this.user.setId(1);
        this.user.setName("Ravi");
        this.user.setEmailId("ravi12@gmail.com");
        this.user.setPassword("$2a$12$Mir1f4z6XtY65J6S8.6Nwuv.g5r5U5xFDmSg.J9XqvZUYkq9miihC");
        this.user.setRole(Role.ADMIN);
        when(this.userService.deleteById(1)).thenReturn(Map.of("message", "Deleted Successfully").toString());
        this.mockMvc.perform(delete("/api/v1/admin/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(this.user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value(Constant.DELETE));
    }
    @AfterAll
    public static void endUserControllerTest() {
        System.out.println("User Controller Test execution finished");
    }
}


