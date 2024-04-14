package com.stefanj.simpleticketingapp.services;

import com.stefanj.simpleticketingapp.model.Comment;

public interface CommentService {
	Comment getById(Long id);
	Comment save(Comment comment);
	void delete(Long id);
}
