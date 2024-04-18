package com.stefanj.simpleticketingapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.stefanj.simpleticketingapp.model.Comment;

@Repository
public interface CommentRepository extends ListCrudRepository<Comment, Long> {
	@Query("FROM Comment c WHERE c.ticket.id = :ticketId ORDER BY c.entityCreatedDate")
	List<Comment> findByTicketId(Long ticketId);
}
