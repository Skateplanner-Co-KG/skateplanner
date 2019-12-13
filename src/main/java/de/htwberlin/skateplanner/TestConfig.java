package de.htwberlin.skateplanner;

import de.htwberlin.skateplanner.event.EventEntity;
import de.htwberlin.skateplanner.event.EventRepository;
import de.htwberlin.skateplanner.security.SecurityConfig;
import de.htwberlin.skateplanner.user.UserEntity;
import de.htwberlin.skateplanner.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class TestConfig {

    @Autowired
    UserRepository userRepository;
    @Autowired
    EventRepository eventRepository;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Bean
    public void generateDummyUsers() {
        userRepository.save(new UserEntity("member@example.com", "member", passwordEncoder.encode("pass"), SecurityConfig.ROLE_MEMBER));
        userRepository.save(new UserEntity("trainer@example.com", "trainer", passwordEncoder.encode("pass2"), SecurityConfig.ROLE_TRAINER));
        eventRepository.save(new EventEntity("some event"));
    }
}
