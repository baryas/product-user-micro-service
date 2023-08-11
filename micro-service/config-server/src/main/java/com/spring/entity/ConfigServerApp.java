package com.spring.entity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ConfigServerApp {
    public static void main(String[] args) {

        SpringApplication.run(ConfigServerApp.class);
    }
}