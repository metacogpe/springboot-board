package com.example.springbootboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan    // thymeleaf3Properties 스캔
@SpringBootApplication
public class SpringbootBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBoardApplication.class, args);
    }

}
