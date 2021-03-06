package de.htwberlin.skateplanner;

import de.htwberlin.skateplanner.event.EventRepository;
import de.htwberlin.skateplanner.user.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableJpaRepositories(basePackageClasses = {UserRepository.class, EventRepository.class})
public class SkateplannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkateplannerApplication.class, args);
    }

}
