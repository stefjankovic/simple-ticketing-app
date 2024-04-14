package com.stefanj.simpleticketingapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.stefanj.simpleticketingapp.model.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Long> {

}
