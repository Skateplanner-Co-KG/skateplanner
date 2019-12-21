package de.htwberlin.skateplanner.registration;

import de.htwberlin.skateplanner.security.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class RegistrationControllerTest {

    private MockMvc mockMvc;
    private RegistrationController registrationController;
    private UserDetailsServiceImpl userDetailsService;

    @BeforeEach
    void setUp() {
        registrationController = new RegistrationController();
        userDetailsService = mock(UserDetailsServiceImpl.class);
        registrationController.userDetailsService = userDetailsService;
        mockMvc = MockMvcBuilders.standaloneSetup(registrationController).build();
    }

    @Test
    void registerUserAccount_Success() throws Exception {
        when(userDetailsService.checkIfEmailIsRegistered(anyString())).thenReturn(false);
        mockMvc.perform(post("/register")
                .param("username", "Hans")
                .param("password", "abc")
                .param("confirmPassword", "abc")
                .param("email", "hans@hans.de"))
                .andExpect(redirectedUrl("login?registered"));
        verify(userDetailsService, times(1)).save(any());
    }

    @Test
    void registerUserAccount_FailurePasswordNotConfirmed() throws Exception {
        when(userDetailsService.checkIfEmailIsRegistered(anyString())).thenReturn(false);
        mockMvc.perform(post("/register")
                .param("username", "Hans")
                .param("password", "abc")
                .param("confirmPassword", "abd")
                .param("email", "hans@hans.de"))
                .andExpect(view().name("register_form"));
        verify(userDetailsService, times(0)).save(any());
    }

    @Test
    void registerUserAccount_FailureEmailAlreadyPresent() throws Exception {
        when(userDetailsService.checkIfEmailIsRegistered(anyString())).thenReturn(true);
        mockMvc.perform(post("/register")
                .param("username", "Hans")
                .param("password", "abc")
                .param("confirmPassword", "abc")
                .param("email", "hans@hans.de"))
                .andExpect(view().name("register_form"));
        verify(userDetailsService, times(0)).save(any());
    }
}