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
import com.stefanj.simpleticketingapp.model.ServiceLayerAgreement;
import com.stefanj.simpleticketingapp.model.Ticket;
import com.stefanj.simpleticketingapp.model.UserGroup;
import com.stefanj.simpleticketingapp.model.UserType;
import com.stefanj.simpleticketingapp.repositories.ServiceLayerAgreementRepository;
import com.stefanj.simpleticketingapp.repositories.TicketRepository;
import com.stefanj.simpleticketingapp.repositories.UserGroupRepository;
import com.stefanj.simpleticketingapp.repositories.UserRepository;
import com.stefanj.simpleticketingapp.services.TicketService;

@Service
public class TicketServiceImpl implements TicketService {
	private static final Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

	private final UserRepository userRepository;
	private final TicketRepository ticketRepository;
	private final ServiceLayerAgreementRepository agreementRepository;
	private final UserGroupRepository userGroupRepository;
	
	public TicketServiceImpl(TicketRepository ticketRepository, UserRepository userRepository, 
			ServiceLayerAgreementRepository agreementRepository, UserGroupRepository userGroupRepository) {
		this.userRepository = userRepository;
		this.ticketRepository = ticketRepository;
		this.agreementRepository = agreementRepository;
		this.userGroupRepository = userGroupRepository;
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
	public Ticket save(Ticket ticket, String authenticatedUserName) {
		logger.debug(getClass().getSimpleName() + ".save: Start.");
		Optional<ServiceLayerAgreement> slaOptional = agreementRepository.findById(ticket.getSla().getId());
		if (slaOptional.isEmpty()) throw new NotFoundException(ErrorCode.RESOURCE_NOT_FOUND, new HashMap<String, Object>(Map.of("slaId", ticket.getSla().getId())));
		ticket.setSla(slaOptional.get());
		ticket.setCreatedBy(userRepository.findByUserName(authenticatedUserName).get());
		
		Optional<UserGroup> userGroupOptional = userGroupRepository.findById(ticket.getUserGroup().getId());
		if (userGroupOptional.isPresent()) ticket.setUserGroup(userGroupOptional.get());
		
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
