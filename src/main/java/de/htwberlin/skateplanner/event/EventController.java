package de.htwberlin.skateplanner.event;

import de.htwberlin.skateplanner.email.EmailReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

@RequestMapping
@Controller
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    EmailReminderService emailReminderService;

    @ModelAttribute("event")
    public EventEntity eventEntity() {
        return new EventEntity();
    }

    @RequestMapping("/planner")
    @ModelAttribute("events")
    public List<EventEntity> getAllEvents() {
        List<EventEntity> result = new LinkedList<>();
        for (EventEntity e: eventRepository.findAll())
            result.add(e);
        return result;
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

        emailReminderService.sendMessageToAllAccounts(
                "New event!",
                "A new event at Skateplanner & Co.KG has been announced!\n" +
                        "   Name: " + event.getName() + "\n" +
                        "   Type: " + event.getType() + "\n" +
                        "   Description:" + event.getDescription() + "\n");

        eventRepository.save(event);
        return "redirect:planner";
    }

    @PostMapping("/delete_event")
    public String deleteEvent(@ModelAttribute("event") EventEntity event, BindingResult result) {
        if (!eventRepository.existsByName(event.getName()))
            result.rejectValue("name", null, "Event not in List");

        if (result.hasErrors())
            return "delete_event_form";

        EventEntity e = eventRepository.findByName(event.getName());

        eventRepository.deleteById(e.getId());
        return "redirect:planner";
    }

}
