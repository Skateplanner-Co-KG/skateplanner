package de.htwberlin.skateplanner.event;

import de.htwberlin.skateplanner.email.EmailReminderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class EventControllerTest {

    private MockMvc mockMvc;
    private EventController eventController;
    private EventRepository eventRepository;

    @BeforeEach
    void setUp() {
        eventController = new EventController();
        eventController.eventRepository = mock(EventRepository.class);
        eventController.emailReminderService = mock(EmailReminderService.class);
        eventRepository = eventController.eventRepository;
        mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();
    }

    @Test
    void addEvent_Success() throws Exception {
        when(eventRepository.existsByName(anyString())).thenReturn(false);
        mockMvc.perform(post("/add_event")
                .param("name", "something")
                .param("type", "something")
                .param("description", "something")
                .param("date", "02-02-2020"))
                .andExpect(redirectedUrl("planner"));
        verify(eventController.emailReminderService, times(1)).sendMessageToAllAccounts(any(), any());
        verify(eventRepository, times(1)).save(any());
    }

    @Test
    void addEvent_FailureDuplicate() throws Exception {
        when(eventRepository.existsByName(anyString())).thenReturn(true);
        mockMvc.perform(post("/add_event")
                .param("name", "something")
                .param("type", "something")
                .param("description", "something")
                .param("date", "02-02-2020"))
                .andExpect(view().name("add_event_form"));
        verify(eventRepository, times(0)).save(any());
    }

    @Test
    void addEvent_FailureMissingName() throws Exception {
        when(eventRepository.existsByName(anyString())).thenReturn(false);
        mockMvc.perform(post("/add_event")
                .param("name", "")
                .param("type", "something")
                .param("description", "something")
                .param("date", "02-02-2020"))
                .andExpect(view().name("add_event_form"));
        verify(eventRepository, times(0)).save(any());
    }
}