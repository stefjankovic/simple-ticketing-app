package com.stefanj.simpleticketingapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.stefanj.simpleticketingapp.config.RsaKeyProperties;
import com.stefanj.simpleticketingapp.model.User;
import com.stefanj.simpleticketingapp.model.UserType;
import com.stefanj.simpleticketingapp.repositories.UserRepository;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
@EnableJpaAuditing
@OpenAPIDefinition(info = @Info(title = "SimpleTicketingApplication API definition", version = "0.1"), security = @SecurityRequirement(name = "Bearer Authentication"))
public class SimpleTicketingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleTicketingApplication.class, args);
	}
	
	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder encoder) {
		return args -> {
			userRepository.save(new User("user", encoder.encode("password"), "user@app.com", UserType.SUPPORT, "Support User", true, null, null));
			userRepository.save(new User("admin", encoder.encode("password"), "admin@app.com", UserType.ADMIN, "Admin Admin", true, null, null));
		};
	}
}
