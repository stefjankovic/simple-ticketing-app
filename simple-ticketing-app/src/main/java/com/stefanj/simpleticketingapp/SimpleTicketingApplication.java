package com.stefanj.simpleticketingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "SimpleTicketingApplication API definition", version = "0.1"))
public class SimpleTicketingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleTicketingApplication.class, args);
	}
}
