package com.school_management.service;

import com.school_management.dto.SignInRequestDTO;
import com.school_management.dto.SignUpRequestDTO;
import com.school_management.entity.User;
import com.school_management.enums.Role;
import com.school_management.exception.UnAuthorizedException;
import com.school_management.exception.UserNotFoundException;
import com.school_management.repository.UserRepository;
import com.school_management.util.Constant;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JWTService jwtService;

    @Mock
    private AuthenticationManager authManager;

    private SignUpRequestDTO signUpDTO;
    private SignInRequestDTO signInDTO;
    private User user;

    @BeforeAll
    public static void toStartUserService() {
        System.out.println("User Service Test case execution has been started");
    }

    @BeforeEach
    void setUp() {
        this.signUpDTO = new SignUpRequestDTO();
        this.signUpDTO.setUserName("John");
        this.signUpDTO.setEmailId("john@example.com");
        this.signUpDTO.setPassword("password");

        this.signInDTO = new SignInRequestDTO();
        this.signInDTO.setEmailId("john@example.com");
        this.signInDTO.setPassword("password");

        this.user = new User();
        this.user.setId(1);
        this.user.setName("John");
        this.user.setEmailId("john@example.com");
        this.user.setPassword(new BCryptPasswordEncoder(12).encode("password"));
        this.user.setRole(Role.ADMIN);
        this.user.setRole(Role.TEACHER);
        this.user.setRole(Role.STUDENT);
    }

    @Test
    void testAdminCreateSuccess() {
        this.user.setRole(Role.ADMIN);
        when(this.userRepository.existsByEmailId(any())).thenReturn(false);
        when(this.userRepository.save(any(User.class))).thenReturn(this.user);

        final User createdUser = this.userService.adminCreate(this.signUpDTO);
        assertEquals("John", createdUser.getName());
        assertEquals(Role.ADMIN, createdUser.getRole());
    }

    @Test
    void testAdminCreateEmailExists() {
        when(this.userRepository.existsByEmailId(any())).thenReturn(true);
        assertThrows(UserNotFoundException.class, () -> this.userService.adminCreate(this.signUpDTO));
    }

    @Test
    void testTeacherCreateSuccess() {
        when(this.userRepository.existsByEmailId(any())).thenReturn(false);
        this.user.setRole(Role.TEACHER);
        when(this.userRepository.save(any(User.class))).thenReturn(this.user);
        final User createUser = this.userService.teacherCreate(this.signUpDTO);
        assertEquals("John", createUser.getName());
        assertEquals(Role.TEACHER, createUser.getRole());

    }

    @Test
    void testTeacherCreateEmailExists() {
        when(this.userRepository.existsByEmailId(any())).thenReturn(true);
        assertThrows(UserNotFoundException.class, () -> this.userService.teacherCreate(this.signUpDTO));
    }

    @Test
    void testStudentCreateSuccess() {
        when(this.userRepository.existsByEmailId(any())).thenReturn(false);
        this.user.setRole(Role.STUDENT);
        when(this.userRepository.save(any(User.class))).thenReturn(this.user);
        final User createUser = this.userService.studentCreate(this.signUpDTO);
        assertEquals("John", createUser.getName());
        assertEquals(Role.STUDENT, createUser.getRole());

    }

    @Test
    void testStudentCreateEmailExists() {
        when(this.userRepository.existsByEmailId(any())).thenReturn(true);
        assertThrows(UserNotFoundException.class, () -> this.userService.studentCreate(this.signUpDTO));
    }

    @Test
    void testSignInSuccess() {
        when(this.userRepository.findByEmailId(this.signInDTO.getEmailId())).thenReturn(Optional.of(this.user));
        when(this.jwtService.generateToken(any())).thenReturn("jwt_token");
        when(this.jwtService.generateRefreshToken(any())).thenReturn("refresh_token");

        final Map<String, String> tokens = this.userService.signIn(this.signInDTO);

        assertEquals("jwt_token", tokens.get("token"));
        assertEquals("refresh_token", tokens.get("refreshToken"));
        verify(this.authManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    void testSignInIncorrectEmail() {
        when(this.userRepository.findByEmailId(any())).thenReturn(Optional.empty());
        assertThrows(AuthorizationDeniedException.class, () -> this.userService.signIn(this.signInDTO));
    }

    @Test
    void testSignInIncorrectPassword() {
        this.user.setPassword(new BCryptPasswordEncoder().encode("wrong_password"));
        when(this.userRepository.findByEmailId(any())).thenReturn(Optional.of(this.user));
        this.signInDTO.setPassword("invalid");

        assertThrows(AuthorizationDeniedException.class, () -> this.userService.signIn(this.signInDTO));
    }

    @Test
    void testRefreshTokenSuccess() {
        when(this.jwtService.extractUserName(any())).thenReturn("john@example.com");
        when(this.userRepository.findByEmailId(any())).thenReturn(Optional.of(this.user));
        when(this.jwtService.validateToken(any(), any())).thenReturn(true);
        when(this.jwtService.generateToken(any())).thenReturn("new_jwt");

        final Map<String, String> refreshed = this.userService.refreshToken("refresh_token");

        assertEquals("new_jwt", refreshed.get("Token"));
        assertEquals("refresh_token", refreshed.get("RefreshToken"));
    }

    @Test
    void testRefreshTokenInvalid() {
        when(this.jwtService.extractUserName(any())).thenReturn("john@example.com");
        when(this.userRepository.findByEmailId(any())).thenReturn(Optional.of(this.user));
        when(this.jwtService.validateToken(any(), any())).thenReturn(false);

        assertThrows(UnAuthorizedException.class, () -> this.userService.refreshToken("refresh_token"));
    }

    @Test
    void testDeleteByIdSuccess() {
        when(this.userRepository.findById(1)).thenReturn(Optional.of(this.user));
        final String result = this.userService.deleteById(1);
        assertEquals(Constant.REMOVE, result);
        verify(this.userRepository).delete(this.user);
    }

    @Test
    void testDeleteByIdUserNotFound() {
        when(this.userRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> this.userService.deleteById(999));
    }

    @Test
    void testGetByIdSuccess() {
        this.user.setRole(Role.ADMIN);
        when(this.userRepository.findById(1)).thenReturn(Optional.of(this.user));

        final User foundUser = this.userService.getById(1);

        assertNotNull(foundUser);
        assertEquals("John", foundUser.getName());
        assertEquals("john@example.com", foundUser.getEmailId());
        assertEquals(Role.ADMIN, foundUser.getRole());
    }

    @Test
    void testGetByIdNotFound() {
        when(this.userRepository.findById(anyInt())).thenReturn(Optional.empty());

        final RuntimeException exception = assertThrows(RuntimeException.class, () -> this.userService.getById(999));
        assertEquals(Constant.DATA_NULL, exception.getMessage());
    }

    @Test
    void testFindByAllAndId() {
        final User user1 = new User();
        user1.setId(1);
        user1.setName("John");
        user1.setEmailId("john@example.com");
        user1.setRole(Role.ADMIN);

        final User user2 = new User();
        user2.setId(2);
        user2.setName("Alice");
        user2.setEmailId("alice@example.com");
        user2.setRole(Role.TEACHER);

        final List<User> userList = List.of(user1, user2);
        when(this.userRepository.findAll()).thenReturn(userList);

        final List<User> result = this.userService.findByAllAndId();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getName());
        assertEquals(Role.TEACHER, result.get(1).getRole());
    }

}
