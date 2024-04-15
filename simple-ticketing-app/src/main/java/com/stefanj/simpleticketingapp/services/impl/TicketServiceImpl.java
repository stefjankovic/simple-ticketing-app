package com.stefanj.simpleticketingapp.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.stefanj.simpleticketingapp.model.Ticket;
import com.stefanj.simpleticketingapp.repositories.TicketRepository;
import com.stefanj.simpleticketingapp.services.TicketService;

@Service
public class TicketServiceImpl implements TicketService {
	private static final Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

	private final TicketRepository ticketRepository;
	
	public TicketServiceImpl(TicketRepository ticketRepository) {
		this.ticketRepository = ticketRepository;
	}

	@Override
	public Ticket getById(Long id) {
		logger.debug(getClass().getSimpleName() + ".getById: id (" + id + ").");
		Optional<Ticket> ticket = ticketRepository.findById(id);
		// TODO if (ticket.isEmpty()) throw new NotFoundException();
		return ticket.get();
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
		ticketRepository.deleteById(id);
	}
}
