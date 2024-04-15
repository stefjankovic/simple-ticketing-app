package com.stefanj.simpleticketingapp.services;

import java.util.List;

import com.stefanj.simpleticketingapp.model.Comment;

public interface CommentService {
	Comment getById(Long id);
	Comment save(Comment comment);
	Comment update(Comment comment);
	void delete(Long id);
	List<Comment> getByTicketId(Long id);
}
