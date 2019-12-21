package de.htwberlin.skateplanner.event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.LinkedList;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

class EventControllerTest {

    private MockMvc mockMvc;
    private EventController eventController;
    private EventRepository eventRepository;

    @BeforeEach
    void setUp() {
        eventController = new EventController();
        eventController.eventRepository = mock(EventRepository.class);
        eventRepository = eventController.eventRepository;
        mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();
        when(eventRepository.findAll()).thenReturn(new LinkedList<>());
    }

    @Test
    void addEvent_Success() throws Exception {
        when(eventRepository.existsByName(anyString())).thenReturn(false);
        mockMvc.perform(post("/add_event")
                .param("name", "something"))
                .andExpect(redirectedUrl("planner"));
        verify(eventRepository, times(1)).save(any());
    }

    @Test
    void addEvent_FailureDuplicate() throws Exception {
        when(eventRepository.existsByName(anyString())).thenReturn(true);
        mockMvc.perform(post("/add_event")
                .param("name", "something"))
                .andExpect(redirectedUrl("add_event?failure"));
        verify(eventRepository, times(0)).save(any());
    }
}