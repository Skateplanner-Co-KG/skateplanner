package de.htwberlin.skateplanner;

import de.htwberlin.skateplanner.security.SecurityConfig;
import de.htwberlin.skateplanner.user.UserEntity;
import de.htwberlin.skateplanner.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Autowired
    UserRepository userRepository;

    @Bean
    public void generateDummyUsers() {
        userRepository.save(new UserEntity("member@example.com", "member", "pass", SecurityConfig.ROLE_MEMBER));
        userRepository.save(new UserEntity("trainer@example.com", "trainer", "pass2", SecurityConfig.ROLE_TRAINER));
    }
}
