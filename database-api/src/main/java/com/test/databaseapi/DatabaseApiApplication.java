package com.test.databaseapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DatabaseApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatabaseApiApplication.class, args);
    }

}
