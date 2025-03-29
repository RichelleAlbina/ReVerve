package com.reverve.reverve;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.reverve.reverve.domain.Login;
import com.reverve.reverve.repository.LoginRepo;
import com.reverve.reverve.service.LoginService;
import com.reverve.reverve.controller.LoginCtrl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

@ExtendWith(MockitoExtension.class)
class ReverveApplicationTests {

    @Mock
    private LoginRepo loginRepo;

    @InjectMocks
    private LoginService loginService;

    @InjectMocks
    private LoginCtrl loginCtrl;

    @Mock
    private Model model;

    private Login user;

    @BeforeEach
    void setUp() {
        // Sample test user
        user = new Login("testuser", "password123");

        // Mocking repository behavior
        lenient().when(loginRepo.FindByUsernameAndPassword("testuser", "password123")).thenReturn(user);
        lenient().when(loginRepo.FindByUsernameAndPassword("wronguser", "wrongpass")).thenReturn(null);

        // Manually injecting the mocked service into the controller
        loginCtrl = new LoginCtrl(loginService);
    }

    @Test
    void testLoginService_ValidUser() {
        when(loginRepo.FindByUsernameAndPassword("testuser", "password123")).thenReturn(user);

        Login result = loginService.login("testuser", "password123");
        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
    }

    @Test
    void testLoginService_InvalidUser() {
        when(loginRepo.FindByUsernameAndPassword("wronguser", "wrongpass")).thenReturn(null);

        Login result = loginService.login("wronguser", "wrongpass");
        assertNull(result);
    }

    @Test
    void testLoginController_ValidLogin() {
        when(loginService.login("testuser", "password123")).thenReturn(user);

        String view = loginCtrl.processlogin("testuser", "password123", model);
        assertEquals("redirect:/welcome", view);
    }

    @Test
    void testLoginController_InvalidLogin() {
        when(loginService.login("wronguser", "wrongpass")).thenReturn(null);

        String view = loginCtrl.processlogin("wronguser", "wrongpass", model);
        assertEquals("login", view);
    }
}
