package com.school_management.service;

import com.school_management.entity.User;
import com.school_management.enums.Role;
import com.school_management.exception.UserNotFoundException;
import com.school_management.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MyUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private MyUserDetailsService myUserDetailsService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.user = new User();
        this.user.setId(1);
        this.user.setName("John");
        this.user.setEmailId("john@example.com");
        this.user.setPassword("password");
        this.user.setRole(Role.ADMIN);
    }

    @Test
    void testLoadUserByUsernameSuccess() {
        when(this.userRepository.findByEmailId("john@example.com")).thenReturn(Optional.of(this.user));

        final UserDetails result = this.myUserDetailsService.loadUserByUsername("john@example.com");

        assertNotNull(result);
        assertEquals("john@example.com", result.getUsername());
        assertEquals("password", result.getPassword());
    }

    @Test
    void testLoadUserByUsernameUserNotFound() {
        when(this.userRepository.findByEmailId("invalid@example.com")).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> this.myUserDetailsService.loadUserByUsername("invalid@example.com"));
    }
}
