package com.stefanj.simpleticketingapp.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.stefanj.simpleticketingapp.exceptions.ErrorCode;
import com.stefanj.simpleticketingapp.exceptions.NotFoundException;
import com.stefanj.simpleticketingapp.model.Comment;
import com.stefanj.simpleticketingapp.model.Ticket;
import com.stefanj.simpleticketingapp.model.User;
import com.stefanj.simpleticketingapp.model.UserType;
import com.stefanj.simpleticketingapp.repositories.CommentRepository;
import com.stefanj.simpleticketingapp.repositories.TicketRepository;
import com.stefanj.simpleticketingapp.repositories.UserRepository;
import com.stefanj.simpleticketingapp.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);
	
	private final TicketRepository ticketRepository;
	private final CommentRepository commentRepository;
	private final UserRepository userRepository;

	public CommentServiceImpl(CommentRepository commentRepository, TicketRepository ticketRepository, UserRepository userRepository) {
		this.commentRepository = commentRepository;
		this.ticketRepository = ticketRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Comment getById(Long id, String authenticatedUserName) {
		logger.debug(getClass().getSimpleName() + ".getById: id (" + id + ").");
		Optional<Comment> comment = commentRepository.findById(id);
		if (comment.isEmpty()) throw new NotFoundException(ErrorCode.RESOURCE_NOT_FOUND, new HashMap<String, Object>(Map.of("id", id)));
		if (!comment.get().getUser().getUserName().equals(authenticatedUserName)) throw new AccessDeniedException("Unauthorized to view other users comments.");
		return comment.get();
	}

	@Override
	public Comment save(Comment comment, String authenticatedUserName) {
		logger.debug(getClass().getSimpleName() + ".save: Start.");
		User user = userRepository.findByUserName(authenticatedUserName).get();
		comment.setUser(user);
		
		Optional<Ticket> ticketOptional = ticketRepository.findById(comment.getTicket().getId());
		if (ticketOptional.isEmpty()) throw new NotFoundException(ErrorCode.RESOURCE_NOT_FOUND, new HashMap<String, Object>(Map.of("ticketId", comment.getTicket().getId())));
		comment.setTicket(ticketOptional.get());
		
		Comment savedComment = commentRepository.save(comment);
		logger.debug(getClass().getSimpleName() + ".save: End. Id = '" + savedComment.getId() + "'.");
		return savedComment;
	}
	
	@Override
	public void delete(Long id) {
		logger.debug(getClass().getSimpleName() + ".delete: id (" + id + ").");
		Optional<Comment> comment = commentRepository.findById(id);
		if (comment.isEmpty()) throw new NotFoundException(ErrorCode.RESOURCE_NOT_FOUND, new HashMap<String, Object>(Map.of("id", id)));
		commentRepository.delete(comment.get());
	}

	@Override
	public List<Comment> getByTicketId(Long ticketId, String authenticatedUserName) {
		logger.debug(getClass().getSimpleName() + ".getByTicketId: ticketId (" + ticketId + ").");
		Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);
		if (ticketOptional.isEmpty()) throw new NotFoundException(ErrorCode.RESOURCE_NOT_FOUND, new HashMap<String, Object>(Map.of("ticketId", ticketId)));
		Ticket ticket = ticketOptional.get();
		
		if (userRepository.findByUserName(authenticatedUserName).get().getType().equals(UserType.ADMIN)) return ticket.getComments();
		if (ticket.getCreatedBy().getUserName().equals(authenticatedUserName) || ticket.getAssignedTo().getUserName().equals(authenticatedUserName)) {
			return ticket.getComments();
		} else {
			throw new AccessDeniedException("Unauthorized to view this ticket.");
		}
	}

	@Override
	public Comment update(Comment comment, String authenticatedUserName) {
		logger.debug(getClass().getSimpleName() + ".update: Start.");
		Optional<Comment> originalCommentOptional = commentRepository.findById(comment.getId());
		if (originalCommentOptional.isEmpty()) throw new NotFoundException(ErrorCode.RESOURCE_NOT_FOUND, new HashMap<String, Object>(Map.of("id", comment.getId())));
		Comment originalComment = originalCommentOptional.get();
		if (originalComment.getUser().getUserName().equals(authenticatedUserName)) {
			originalComment.setText(comment.getText());
			Comment updatedComment = commentRepository.save(originalComment);
			logger.debug(getClass().getSimpleName() + ".update: End. Id = '" + updatedComment.getId() + "'.");
			return updatedComment;
		} else {
			throw new AccessDeniedException("Unauthorized to edit comment.");
		}
	}
}
