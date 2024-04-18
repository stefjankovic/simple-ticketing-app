package com.stefanj.simpleticketingapp.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stefanj.simpleticketingapp.dtos.TicketDTO;
import com.stefanj.simpleticketingapp.services.TicketService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Ticket")
@RequestMapping(path="/api/v1/tickets", produces="application/json")
public class TicketController {
	private static final Logger logger = LoggerFactory.getLogger(TicketController.class);

	private final TicketService ticketService;
	
	public TicketController(TicketService ticketService) {
		this.ticketService = ticketService;
	}
	
	@GetMapping
	@Operation(summary = "List all tickets")
	public ResponseEntity<List<TicketDTO>> getTickets(Authentication authentication) {
		logger.debug(getClass().getSimpleName() + ".getTickets: Start.");
		return new ResponseEntity<>(ticketService.getAllForUser(authentication.getName()).stream().map(TicketDTO::fromEntity).toList(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Get ticket", description = "Get ticket by id")
	public ResponseEntity<TicketDTO> getTicketById(@PathVariable @Parameter(description = "Ticket Id") Long id, Authentication authentication) {
		logger.debug(getClass().getSimpleName() + ".getTicketById: Called for id (" + id + ").");
		return new ResponseEntity<>(TicketDTO.fromEntity(ticketService.getById(id, authentication.getName())), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('SCOPE_Customer')")
	@PostMapping
	@Operation(summary = "Create ticket")
	public ResponseEntity<TicketDTO> createTicket(TicketDTO ticketDTO, Authentication authentication) {
		logger.debug(getClass().getSimpleName() + ".createTicket: Called for " + ticketDTO + ".");
		return new ResponseEntity<>(TicketDTO.fromEntity(ticketService.create(TicketDTO.toEntity(ticketDTO), authentication.getName())), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAuthority('SCOPE_SupportStaff') or hasAuthority('SCOPE_Admin')")
	@PutMapping
	@Operation(summary = "Update ticket")
	public ResponseEntity<TicketDTO> updateTicket(TicketDTO ticketDTO, Authentication authentication) {
		logger.debug(getClass().getSimpleName() + ".updateTicket: Called for " + ticketDTO + ".");
		return new ResponseEntity<>(TicketDTO.fromEntity(ticketService.update(TicketDTO.toEntity(ticketDTO), authentication.getName())), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('SCOPE_Customer') or hasAuthority('SCOPE_Admin')")
	@DeleteMapping("/{id}")
	@Operation(summary = "Delete ticket")
	public ResponseEntity<String> deleteTicket(@PathVariable @Parameter(description = "Ticket Id") Long id) {
		logger.debug(getClass().getSimpleName() + ".deleteTicket: Called for id (" + id + ").");
		ticketService.delete(id);
		return ResponseEntity.ok().build();
	}
}
