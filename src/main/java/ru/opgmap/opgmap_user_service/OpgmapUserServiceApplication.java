package ru.opgmap.opgmap_user_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OpgmapUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpgmapUserServiceApplication.class, args);
    }

}
