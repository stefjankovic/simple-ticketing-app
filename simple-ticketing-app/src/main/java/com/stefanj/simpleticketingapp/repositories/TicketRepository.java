package com.stefanj.simpleticketingapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stefanj.simpleticketingapp.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
