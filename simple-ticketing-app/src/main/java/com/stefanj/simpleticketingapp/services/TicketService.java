package com.stefanj.simpleticketingapp.services;

import com.stefanj.simpleticketingapp.model.Ticket;

public interface TicketService {
	Ticket getById(Long id);
	Ticket save(Ticket ticket);
	void delete(Long id);
}
