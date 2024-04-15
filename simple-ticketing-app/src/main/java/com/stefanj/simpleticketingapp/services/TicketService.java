package com.stefanj.simpleticketingapp.services;

import java.util.List;

import com.stefanj.simpleticketingapp.model.Ticket;

public interface TicketService {
	Ticket getById(Long id);
	Ticket save(Ticket ticket);
	Ticket update(Ticket ticket);
	void delete(Long id);
	List<Ticket> getAll();
}
