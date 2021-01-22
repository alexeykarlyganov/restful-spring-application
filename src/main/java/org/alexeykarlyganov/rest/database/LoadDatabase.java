package org.alexeykarlyganov.rest.database;

import org.alexeykarlyganov.rest.repositories.UserRepository;
import org.alexeykarlyganov.rest.models.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {
        return args -> {
            logger.info("Preloading " + repository.save(new User("User1", "user1@email.com")));
            logger.info("Preloading " + repository.save(new User("User2", "user2@email.com")));
        };
    }
}
