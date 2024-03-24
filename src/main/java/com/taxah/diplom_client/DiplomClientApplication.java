package com.taxah.diplom_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * The main class of the application. It is used to start the application.
 * Ready for use Feign Clients.
 */
@SpringBootApplication
@EnableFeignClients
public class DiplomClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiplomClientApplication.class, args);
    }

}
