package de.htwberlin.skateplanner.fragment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class fragmentController {
    @RequestMapping("/")
    public String planner(){
        return "planner.html";
    }

    @RequestMapping("/contact")
    public String contact(){
        return "contact.html";
    }
    @RequestMapping("/about_us")
    public String about_us(){
        return "about_us.html";
    }
}
