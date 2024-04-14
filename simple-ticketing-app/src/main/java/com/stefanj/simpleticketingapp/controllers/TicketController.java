package com.stefanj.simpleticketingapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stefanj.simpleticketingapp.dtos.TicketDTO;
import com.stefanj.simpleticketingapp.services.TicketService;

@RestController
@RequestMapping(path="/api/v1/tickets", produces="application/json")
public class TicketController {
	private static final Logger logger = LoggerFactory.getLogger(TicketController.class);

	private final TicketService ticketService;
	
	public TicketController(TicketService ticketService) {
		this.ticketService = ticketService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TicketDTO> getTicketById(@PathVariable Long id) {
		logger.debug(getClass().getSimpleName() + ".getTicketById: Called for id (" + id + ").");
		return new ResponseEntity<>(TicketDTO.fromEntity(ticketService.getById(id)), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<TicketDTO> createTicket(TicketDTO ticketDTO) {
		logger.debug(getClass().getSimpleName() + ".createTicket: Called for " + ticketDTO + ".");
		return new ResponseEntity<>(TicketDTO.fromEntity(ticketService.save(TicketDTO.toEntity(ticketDTO))), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<TicketDTO> updateTicket(TicketDTO ticketDTO) {
		logger.debug(getClass().getSimpleName() + ".updateTicket: Called for " + ticketDTO + ".");
		return new ResponseEntity<>(TicketDTO.fromEntity(ticketService.save(TicketDTO.toEntity(ticketDTO))), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTicket(@PathVariable Long id) {
		logger.debug(getClass().getSimpleName() + ".deleteTicket: Called for id (" + id + ").");
		ticketService.delete(id);
		return ResponseEntity.ok().build();
	}
}
