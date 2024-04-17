package com.stefanj.simpleticketingapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stefanj.simpleticketingapp.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
	@Query("FROM Ticket t WHERE t.createdBy.userName = :userName OR t.assignedTo.userName = :userName")
	List<Ticket> findByUser(String userName);
}
