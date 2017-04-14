package com.gmail.at.irotech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.gmail.at.irotech.web.rest", "com.gmail.at.irotech.service"})
@SpringBootApplication
public class RoboHooverApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoboHooverApplication.class, args);
    }

}
