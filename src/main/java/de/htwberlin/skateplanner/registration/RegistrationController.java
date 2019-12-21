package de.htwberlin.skateplanner.registration;

import de.htwberlin.skateplanner.security.UserDetailsServiceImpl;
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
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "register";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto, BindingResult result) {
        if (userDetailsService.checkIfEmailIsRegistered(userDto.getEmail())) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            result.rejectValue("password", null, "Password and Confirmed Password do not match");
        }
        if (result.hasErrors()) {
            return "forward:register";
        }
        userDetailsService.save(userDto);
        return "redirect:login?registered";
    }

}
