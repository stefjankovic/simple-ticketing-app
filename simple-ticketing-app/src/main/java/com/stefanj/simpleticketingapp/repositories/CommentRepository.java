package com.stefanj.simpleticketingapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stefanj.simpleticketingapp.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	@Query("FROM Comment c WHERE c.ticket.id = :ticketId ORDER BY c.entityCreatedDate")
	List<Comment> findByTicketId(Long ticketId);
}
