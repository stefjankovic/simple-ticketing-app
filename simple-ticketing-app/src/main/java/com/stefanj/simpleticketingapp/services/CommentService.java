package com.stefanj.simpleticketingapp.services;

import java.util.List;

import com.stefanj.simpleticketingapp.model.Comment;

public interface CommentService {
	Comment getById(Long id, String authenticatedUserName);
	Comment save(Comment comment, String authenticatedUserName);
	Comment update(Comment comment, String authenticatedUserName);
	void delete(Long id);
	List<Comment> getByTicketId(Long id, String authenticatedUserName);
}
