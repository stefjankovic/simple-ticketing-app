package com.stefanj.simpleticketingapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.stefanj.simpleticketingapp.model.Ticket;

@Repository
public interface TicketRepository extends ListCrudRepository<Ticket, Long> {
	@Query("FROM Ticket t WHERE t.createdBy.userName = :userName OR t.assignedTo.userName = :userName")
	List<Ticket> findByUser(String userName);
}
