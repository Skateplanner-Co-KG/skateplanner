package de.htwberlin.skateplanner.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping
@Controller
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @ModelAttribute("event")
    public EventEntity eventEntity() {
        return new EventEntity();
    }

    @RequestMapping("/planner")
    @ModelAttribute("events")
    public Iterable<EventEntity> getAllEvents() {
        return eventRepository.findAll();
    }

    @GetMapping("/add_event")
    public String showAddEventForm(Model model) {
        return "add_event_form";
    }

    @GetMapping("/delete_event")
    public String showDeleteEventForm(Model model) { return "delete_event_form"; }

    @PostMapping("/add_event")
    public String addEvent(@ModelAttribute("event") @Valid EventEntity event, BindingResult result) {

        if (eventRepository.existsByName(event.getName()))
            result.rejectValue("name", null, "Eventname is already taken");

        if (result.hasErrors())
            return "add_event_form";

        eventRepository.save(event);
        return "redirect:planner";
    }

    @PostMapping("/delete_event")
    public void deleteEvent(@ModelAttribute("event") @Valid EventEntity event, BindingResult result) {

        if (eventRepository.existsById(event.getId()));
        eventRepository.deleteById(event.getId());
    }

}
