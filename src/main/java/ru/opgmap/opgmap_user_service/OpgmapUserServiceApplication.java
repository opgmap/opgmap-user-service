package ru.opgmap.opgmap_user_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@SpringBootApplication
@EnableEurekaClient
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL_FORMS)
public class OpgmapUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpgmapUserServiceApplication.class, args);
    }

}
