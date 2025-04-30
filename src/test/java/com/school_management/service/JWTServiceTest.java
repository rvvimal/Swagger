package com.school_management.service;

import com.school_management.entity.User;
import com.school_management.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class JWTServiceTest {

    private JWTService jwtService;
    private User user;

    @BeforeEach
    void setUp() {
        this.jwtService = new JWTService();
        this.user = new User();
        this.user.setId(1);
        this.user.setName("John");
        this.user.setEmailId("john@example.com");
        this.user.setPassword("password");
        this.user.setRole(Role.ADMIN);
    }

    @Test
    void testGenerateTokenAndExtractUsername() {
        final String token = this.jwtService.generateToken(this.user);

        assertNotNull(token);
        final String username = this.jwtService.extractUserName(token);

        assertEquals(this.user.getUsername(), username);
    }

    @Test
    void testGenerateRefreshToken() {
        final String refreshToken = this.jwtService.generateRefreshToken(this.user);

        assertNotNull(refreshToken);
        assertFalse(refreshToken.isEmpty());
    }

    @Test
    void testValidateToken_ValidToken() {
        final String token = this.jwtService.generateToken(this.user);

        boolean isValid = this.jwtService.validateToken(token, this.user);
        assertTrue(isValid);
    }

    @Test
    void testValidateToken_InvalidUsername() {
        final String token = this.jwtService.generateToken(this.user);

        final User otherUser = new User();
        otherUser.setEmailId("other@example.com");

        boolean isValid = this.jwtService.validateToken(token, otherUser);
        assertFalse(isValid);
    }

    @Test
    void testExtractExpiration_NotExpired() {
        final String token = this.jwtService.generateToken(this.user);

        assertFalse(this.jwtService.validateToken(token, this.user) && this.jwtService.extractUserName(token).isEmpty());
    }
}
