package de.htwberlin.skateplanner.static_page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StaticPageController {
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
