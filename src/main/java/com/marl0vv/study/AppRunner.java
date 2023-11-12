package com.marl0vv.study;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {
    @Value("${input.data.path}")
    private String inputDataPath;

    private final Storage storage;

    public AppRunner(Storage storage) {
        this.storage = storage;
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public CommandLineRunner setupData(Storage storage) {
        return args -> {
            storage.initializeStorage(inputDataPath);
        };
    }

    @Override
    public void run(String... args) {
        Service service = new Service(storage);
        service.run();
    }
}
