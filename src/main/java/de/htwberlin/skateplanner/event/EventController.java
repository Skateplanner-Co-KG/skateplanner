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

@Controller
@RequestMapping("/planner")
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @ModelAttribute("event")
    public EventEntity eventEntity() {
        return new EventEntity();
    }

    @ModelAttribute("events")
    public Iterable<EventEntity> getAllEvents() {
        return eventRepository.findAll();
    }

    @GetMapping
    public String showPlannerForm(Model model) {
        return "planner";
    }

    @PostMapping
    public String addEvent(@ModelAttribute("event") @Valid EventEntity event, BindingResult result) {
        System.out.println("test1");
        if (eventRepository.existsByName(event.getName()))
            result.rejectValue("name", null, "Eventname is already taken");
        System.out.println("test2");

        eventRepository.save(event);
        System.out.println("test3");

        return "planner";
    }

}
