package com.example.rest_web_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication()
public class RestWebServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestWebServiceApplication.class, args);
    }

}
