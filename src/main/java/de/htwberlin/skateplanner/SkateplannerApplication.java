package de.htwberlin.skateplanner;

import de.htwberlin.skateplanner.model.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class SkateplannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkateplannerApplication.class, args);
    }

}
