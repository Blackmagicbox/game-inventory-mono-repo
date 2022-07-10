package com.api.gameinventory.backend;

import com.api.gameinventory.backend.game.Game;
import com.api.gameinventory.backend.game.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDB {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoadDB.class);

    @Bean
    CommandLineRunner initDB(GameRepository repository) {
        return args -> {
            LOGGER.info("Preloading " + repository.save(new Game("Super Mario Bros.", "NES", "Platform")));
            LOGGER.info("Preloading " + repository.save(new Game("Donkey Kong Country.", "SNES", "Platform")));
            LOGGER.info("Preloading " + repository.save(new Game("Sonic the Hedgehog.", "Genesis", "Platform")));
        };
    }
}


//            Model Example:
//            LOGGER.info("Preloading " + repository.save(new Game("Super Mario Bros.", "Nintendo", "NES", "1985", "Super Mario Bros. is a platform video game series created by Nintendo for the Nintendo Entertainment System (NES) console. It is the first installment in the Mario series, and the first in the series to be released for the NES. The series was originally published in Japan, and was later ported to North America and Europe. The series was also ported to the");

