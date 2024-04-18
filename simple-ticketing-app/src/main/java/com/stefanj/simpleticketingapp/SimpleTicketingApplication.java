package com.stefanj.simpleticketingapp;

import java.util.Collections;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.stefanj.simpleticketingapp.config.RsaKeyProperties;
import com.stefanj.simpleticketingapp.model.SLAPriority;
import com.stefanj.simpleticketingapp.model.ServiceLayerAgreement;
import com.stefanj.simpleticketingapp.model.Ticket;
import com.stefanj.simpleticketingapp.model.TicketCategory;
import com.stefanj.simpleticketingapp.model.TicketStatus;
import com.stefanj.simpleticketingapp.model.User;
import com.stefanj.simpleticketingapp.model.UserType;
import com.stefanj.simpleticketingapp.repositories.ServiceLayerAgreementRepository;
import com.stefanj.simpleticketingapp.repositories.TicketRepository;
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
	CommandLineRunner commandLineRunner(UserRepository userRepository, TicketRepository ticketRepository,
            ServiceLayerAgreementRepository serviceLayerAgreementRepository, PasswordEncoder encoder) {
		User support = new User("user", encoder.encode("password"), "user@app.com", UserType.SUPPORT, "Support User", true, null, null);
	    User admin = new User("admin", encoder.encode("password"), "admin@app.com", UserType.ADMIN, "Admin Admin", true, null, null);
	    User customer = new User("customer", encoder.encode("password"), "customer@app.com", UserType.CUSTOMER, "Customer User", true, null, null);
	    ServiceLayerAgreement sla = new ServiceLayerAgreement("SLA1", "SLA1", SLAPriority.MEDIUM, 10, 20);
	    return args -> {
	       userRepository.save(admin);
	       userRepository.save(support);
	       userRepository.save(customer);
	       serviceLayerAgreementRepository.save(sla);
	       ticketRepository.save(new Ticket("Logging issue", "User is not able to login", TicketCategory.INCIDENT, TicketStatus.OPEN, sla, null, support, customer, null, null, Collections.emptyList()));
	    };
	}
}
