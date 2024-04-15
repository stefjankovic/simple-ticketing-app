package com.stefanj.simpleticketingapp.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.stefanj.simpleticketingapp.exceptions.ErrorCode;
import com.stefanj.simpleticketingapp.exceptions.NotFoundException;
import com.stefanj.simpleticketingapp.model.Comment;
import com.stefanj.simpleticketingapp.repositories.CommentRepository;
import com.stefanj.simpleticketingapp.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);
	
	private final CommentRepository commentRepository;

	public CommentServiceImpl(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	@Override
	public Comment getById(Long id) {
		logger.debug(getClass().getSimpleName() + ".getById: id (" + id + ").");
		Optional<Comment> comment = commentRepository.findById(id);
		if (comment.isEmpty()) throw new NotFoundException(ErrorCode.RESOURCE_NOT_FOUND, new HashMap<String, Object>(Map.of("id", id)));
		return comment.get();
	}

	@Override
	public Comment save(Comment comment) {
		logger.debug(getClass().getSimpleName() + ".save: Start.");
		Comment savedComment = commentRepository.save(comment);
		logger.debug(getClass().getSimpleName() + ".save: End. Id = '" + savedComment.getId() + "'.");
		return savedComment;
	}
	
	// TODO add update method

	@Override
	public void delete(Long id) {
		logger.debug(getClass().getSimpleName() + ".delete: id (" + id + ").");
		Optional<Comment> comment = commentRepository.findById(id);
		if (comment.isEmpty()) throw new NotFoundException(ErrorCode.RESOURCE_NOT_FOUND, new HashMap<String, Object>(Map.of("id", id)));
		commentRepository.delete(comment.get());
	}

	@Override
	public List<Comment> getByTicketId(Long id) {
		logger.debug(getClass().getSimpleName() + ".getByTicketId: id (" + id + ").");
		return commentRepository.findByTicketId(id);
	}
}
