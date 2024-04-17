package com.stefanj.simpleticketingapp.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.stefanj.simpleticketingapp.exceptions.ErrorCode;
import com.stefanj.simpleticketingapp.exceptions.NotFoundException;
import com.stefanj.simpleticketingapp.model.Ticket;
import com.stefanj.simpleticketingapp.model.UserType;
import com.stefanj.simpleticketingapp.repositories.TicketRepository;
import com.stefanj.simpleticketingapp.repositories.UserRepository;
import com.stefanj.simpleticketingapp.services.TicketService;

@Service
public class TicketServiceImpl implements TicketService {
	private static final Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

	private final UserRepository userRepository;
	private final TicketRepository ticketRepository;
	
	public TicketServiceImpl(TicketRepository ticketRepository, UserRepository userRepository) {
		this.userRepository = userRepository;
		this.ticketRepository = ticketRepository;
	}

	@Override
	public Ticket getById(Long id, String authenticatedUserName) {
		logger.debug(getClass().getSimpleName() + ".getById: id (" + id + ").");
		Optional<Ticket> ticketOptional = ticketRepository.findById(id);
		if (ticketOptional.isEmpty()) throw new NotFoundException(ErrorCode.RESOURCE_NOT_FOUND, new HashMap<String, Object>(Map.of("id", id)));
		if (userRepository.findByUserName(authenticatedUserName).get().getType().equals(UserType.ADMIN)) return ticketOptional.get();
		if (ticketOptional.get().getCreatedBy().getUserName().equals(authenticatedUserName) ||
				ticketOptional.get().getAssignedTo().getUserName().equals(authenticatedUserName)) return ticketOptional.get();
		throw new AccessDeniedException("Unauthorized. Ticket not assigned to user.");
	}

	@Override
	public Ticket save(Ticket ticket) {
		logger.debug(getClass().getSimpleName() + ".save: Start.");
		Ticket savedTicket = ticketRepository.save(ticket);
		logger.debug(getClass().getSimpleName() + ".save: End. Id = '" + savedTicket.getId() + "'.");
		return savedTicket;
	}

	@Override
	public void delete(Long id) {
		logger.debug(getClass().getSimpleName() + ".delete: id (" + id + ").");
		Optional<Ticket> ticketOptional = ticketRepository.findById(id);
		if (ticketOptional.isEmpty()) throw new NotFoundException(ErrorCode.RESOURCE_NOT_FOUND, new HashMap<String, Object>(Map.of("id", id)));
		ticketRepository.delete(ticketOptional.get());
	}

	@Override
	public List<Ticket> getAllForUser(String authenticatedUserName) {
		logger.debug(getClass().getSimpleName() + ".getAll: Start.");
		if (userRepository.findByUserName(authenticatedUserName).get().getType().equals(UserType.ADMIN)) return ticketRepository.findAll();
		return ticketRepository.findByUser(authenticatedUserName);
	}

	@Override
	public Ticket update(Ticket ticket) {
		logger.debug(getClass().getSimpleName() + ".update: Start.");
		if (!ticketRepository.existsById(ticket.getId())) throw new NotFoundException(ErrorCode.RESOURCE_NOT_FOUND, new HashMap<String, Object>(Map.of("id", ticket.getId())));
		Ticket updatedTicket = ticketRepository.save(ticket);
		logger.debug(getClass().getSimpleName() + ".update: End. Id = '" + updatedTicket.getId() + "'.");
		return updatedTicket;
	}
}
