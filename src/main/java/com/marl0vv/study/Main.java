package com.marl0vv.study;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Value("${input.data.path}")
    private String inputDataPath;

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public CommandLineRunner setupData(Storage storage) {
        return args -> {
            storage.initializeStorage(inputDataPath);
        };
    }

}
