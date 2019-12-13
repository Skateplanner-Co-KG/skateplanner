package de.htwberlin.skateplanner;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/about_us").setViewName("about_us");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/planner").setViewName("planner");
        registry.addViewController("/contact").setViewName("contact");
        registry.addViewController("/add_event").setViewName("add_event");
        registry.addViewController("/register").setViewName("register");
    }

}