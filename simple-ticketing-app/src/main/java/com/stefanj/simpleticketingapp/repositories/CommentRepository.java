package com.stefanj.simpleticketingapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.stefanj.simpleticketingapp.model.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {

}
