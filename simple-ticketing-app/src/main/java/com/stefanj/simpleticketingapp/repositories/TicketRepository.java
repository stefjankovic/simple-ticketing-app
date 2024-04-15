package com.stefanj.simpleticketingapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stefanj.simpleticketingapp.model.Ticket;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {

}
