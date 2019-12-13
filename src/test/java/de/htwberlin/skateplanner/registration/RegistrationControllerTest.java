package de.htwberlin.skateplanner.registration;

import de.htwberlin.skateplanner.security.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
    void registerUserAccount() throws Exception {
        when(userDetailsService.checkIfEmailIsRegistered(anyString())).thenReturn(false);
        UserRegistrationDto dto = new UserRegistrationDto("Hans", "abc", "abc", "hans@hans.de");
        when(userDetailsService.save(any())).thenReturn(null);

        assertEquals(mockMvc.perform(post("/register", dto)).andReturn(), "redirect:/login?registered");
        verify(userDetailsService, times(1)).save(dto);
    }
}