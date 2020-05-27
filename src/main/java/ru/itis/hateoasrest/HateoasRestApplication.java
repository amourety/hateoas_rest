package ru.itis.hateoasrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class HateoasRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(HateoasRestApplication.class, args);
    }


}
