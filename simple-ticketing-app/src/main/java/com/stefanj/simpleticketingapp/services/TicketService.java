package com.stefanj.simpleticketingapp.services;

import java.util.List;

import com.stefanj.simpleticketingapp.model.Ticket;

public interface TicketService {
	Ticket getById(Long id, String authenticatedUserName);
	Ticket create(Ticket ticket, String authenticatedUserName);
	Ticket update(Ticket ticket, String authenticatedUserName);
	void delete(Long id);
	List<Ticket> getAllForUser(String authenticatedUserName);
}
